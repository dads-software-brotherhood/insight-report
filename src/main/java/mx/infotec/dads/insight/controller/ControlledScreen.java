package mx.infotec.dads.insight.controller;

@FunctionalInterface
public interface ControlledScreen {
    /**
     * This method will allow the injection of the Parent ScreenPane
     * 
     * @param screenPage
     */
    public void setScreenParent(ScreensController screenPage);
}
