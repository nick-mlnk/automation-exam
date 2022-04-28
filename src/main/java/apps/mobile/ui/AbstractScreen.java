package apps.mobile.ui;

import infrastructure.drivers.MobileDriver;

public abstract class AbstractScreen extends MobileDriver {

    // skip setup for base page


    protected AbstractScreen() {
        waitUntilLoaded();
    }

    protected void waitUntilLoaded() {
    }

}
