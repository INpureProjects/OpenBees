package info.inpureprojects.OpenBees.Client.Gui.Tabs;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.TabBase;
import cpw.mods.fml.common.registry.LanguageRegistry;

import java.util.List;

/**
 * Created by den on 8/8/2014.
 */
public class TabApiaryStatus extends TabBase {

    private String text = "";

    public TabApiaryStatus(GuiBase guiBase) {
        super(guiBase);
        this.backgroundColor = 0x00CCFF;
        this.maxHeight = 92;
        this.maxWidth = 100;
        this.textColor = 0xFFFFFF;
    }

    @Override
    public void draw() {
        drawBackground();
        drawTabIcon("scoop");
        if (!isFullyOpened()) {
            return;
        }
    }

    @Override
    public void addTooltip(List<String> strings) {
        super.addTooltip(strings);
        strings.add(LanguageRegistry.instance().getStringLocalization(this.text));
    }

    public void setText(String text) {
        this.text = text;
    }
}
