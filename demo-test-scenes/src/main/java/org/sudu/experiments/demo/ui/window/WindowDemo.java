package org.sudu.experiments.demo.ui.window;

import org.sudu.experiments.SceneApi;
import org.sudu.experiments.WglGraphics;
import org.sudu.experiments.demo.Scene1;
import org.sudu.experiments.demo.TestHelper;
import org.sudu.experiments.demo.ui.*;
import org.sudu.experiments.fonts.Fonts;
import org.sudu.experiments.input.KeyCode;
import org.sudu.experiments.input.KeyEvent;
import org.sudu.experiments.input.MouseEvent;
import org.sudu.experiments.math.*;

import java.util.function.Supplier;

import static org.sudu.experiments.Const.emptyRunnable;

public class WindowDemo extends Scene1 implements DprChangeListener {

  private final PopupMenu popupMenu;

  private final WindowManager windowManager;
  private Window window1, window2;

  public WindowDemo(SceneApi api) {
    super(api);
    windowManager = new WindowManager();
    uiContext.dprListeners.add(windowManager);
    uiContext.dprListeners.add(this);
    clearColor.set(new Color(43));

    popupMenu = new PopupMenu(uiContext);
    popupMenu.setTheme(DialogItemColors.darkColorScheme());
    popupMenu.setFont(new UiFont("Consolas", 25));

    api.input.onKeyPress.add(this::onKey);
    api.input.onContextMenu.add(this::onContextMenu);
    api.input.onMouse.add(TestHelper.popupMouseListener(popupMenu));
    api.input.onMouse.add(windowManager);
    api.input.onScroll.add(windowManager::onScroll);
  }

  @Override
  public void onDprChanged(float oldDpr, float newDpr) {
    if (oldDpr == 0) openWindows();
  }

  @Override
  public void dispose() {
    popupMenu.dispose();
    windowManager.dispose();
  }

  @Override
  public void paint() {
    super.paint();
    WglGraphics graphics = api.graphics;
    windowManager.draw(graphics);
    popupMenu.paint();
  }

  private boolean onContextMenu(MouseEvent event) {
    if (!popupMenu.isVisible()) {
      popupMenu.display(event.position, items(), emptyRunnable);
    }
    return true;
  }

  private Supplier<ToolbarItem[]> items() {
    return ArrayOp.supplier(
        new ToolbarItem(this::openWindows, "newWindow",
            popupMenu.theme().toolbarItemColors)
    );
  }

  private void openWindows() {
    disposeWindow(window1);
    disposeWindow(window2);

    window1 = newWindow("Window1", .5f);
    windowManager.addWindow(window1);
    window2 = newWindow("Window2", 1);
    windowManager.addWindow(window2);
    layoutWindows();
  }

  private void disposeWindow(Window w) {
    if (w != null) {
      windowManager.removeWindow(w);
      w.dispose();
    }
  }
  UiFont titleFont = new UiFont(Fonts.SegoeUI, 15);

  private Window newWindow(String title, float v) {
    Window window = new Window(uiContext);
    window.setContent(new ScrollView(new ScrollContentDemo(v), uiContext));
    window.setTheme(DialogItemColors.darkColorScheme());
    window.setTitle(title, titleFont);
    return window;
  }

  @Override
  public void onResize(V2i newSize, float newDpr) {
    super.onResize(newSize, newDpr);
    windowManager.onResize(newSize, newDpr);
    layoutWindows();
  }

  private void layoutWindows() {
    V2i newSize = uiContext.windowSize;
    window1.setPosition(
        new V2i(newSize.x * 4 / 10, newSize.y * 4 / 10),
        new V2i(newSize.x * 5 / 10, newSize.y * 5 / 10)
    );
    window2.setPosition(
        new V2i(newSize.x / 10, newSize.y / 10),
        new V2i(newSize.x * 6 / 10, newSize.y * 6 / 10)
    );
    window1.setTitle("Window 1: " + window1.size().toString(), titleFont);
    window2.setTitle("Window 2: " + window2.size().toString(), titleFont);
  }


  private boolean onKey(KeyEvent event) {
    if (event.isPressed && event.keyCode == KeyCode.SPACE) {
      return true;
    }
    return false;
  }

  @Override
  public boolean update(double timestamp) {
    return windowManager.update(timestamp);
  }
}