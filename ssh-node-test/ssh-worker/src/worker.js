console.log(`Hello, from worker thread`);

import {newSshClient} from "./ssh_mjs.mjs";

import {parentPort} from 'node:worker_threads';

const connectMap = new Map();

function connect(config) {
  return new Promise(
      (resolve, reject) => {
        const connection = newSshClient();
        connection.on("ready", () => {
          console.log('connection: ready', config);
          connection.sftp((err, sftp) => {
            if (err) reject(err);
            else resolve({connection, sftp});
          });
        }).connect(config);
      }
  );
}

function getConnection(config) {
  const key = JSON.stringify(config);
  let promise = connectMap.get(key);
  if (!promise) {
    promise = connect(config);
    connectMap.set(key, promise);
  }
  return promise;
}

function finish(pair, key) {
  console.log("connection closed: ", key);
  pair.connection.end();
  parentPort.postMessage(["finished"]);
}

function listFiles(error, list, path) {
  if (error)
    list = [];
  parentPort.postMessage(["listed", path, list]);
}

function readDir(config, path) {
  const conn = getConnection(config);
  conn.then(pair => {
    pair.sftp.readdir(path,
        (error, list) => listFiles(error, list, path));
  });
}

function close(config) {
  const key = JSON.stringify(config);
  let promise = connectMap.get(key);
  if (promise) {
    connectMap.delete(key);
    promise.then(pair => finish(pair, key));
  }
}

parentPort.onmessage = function (message) {
  let data = message.data;
  console.log(`Worker: `, data);
  if ('cmd' in data) {
    console.log(`cmd =`, data.cmd);
    switch (data.cmd) {
      case "readDir":
        console.log(`readDir, ssh=`, data.ssh);
        readDir(data.ssh, data.path);
        break;
      case "close":
        console.log(`closing ssh=`, data.ssh);
        close(data.ssh);
    }
  }

}
