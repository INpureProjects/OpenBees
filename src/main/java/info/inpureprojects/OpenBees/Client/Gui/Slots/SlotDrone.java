package info.inpureprojects.OpenBees.Client.Gui.Slots;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeUtils;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/8/2014.
 */
public class SlotDrone extends SlotCustom {

    public SlotDrone(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (BeeUtils.instance.isDrone(stack)) {
            return true;
        }
        return false;
    }
}
