package info.inpureprojects.OpenBees.Common.WorldGen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by den on 8/11/2014.
 */
public class WorldGenHives implements IWorldGenerator {

    private static int amountPerChunk = 1;
    private static List<ItemStack> replaceable = new ArrayList();
    private static List<ItemStack> canSitOn = new ArrayList();

    static {
        replaceable.addAll(OreDictionary.getOres("treeLeaves"));
        replaceable.add(new ItemStack(Blocks.snow_layer, 1, 0));
        canSitOn.add(new ItemStack(Blocks.grass, 1, 0));
        canSitOn.add(new ItemStack(Blocks.sand, 1, 0));
    }

    public WorldGenHives() {
    }

    public static void init() {
        GameRegistry.registerWorldGenerator(new WorldGenHives(), 9999);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        for (int i = 0; i < amountPerChunk; i++) {
            int randX = this.makeRandomCoord(chunkX, random, 16);
            int randY = random.nextInt(90 - 62) + 62;
            int randZ = this.makeRandomCoord(chunkZ, random, 16);
            // Landed in air.
            if (world.isAirBlock(randX, randY, randZ)) {
                // Is something under us?
                if (!world.isAirBlock(randX, randY - 1, randZ)) {
                    // What is it?
                    int meta = world.getBlockMetadata(randX, randY - 1, randZ);
                    this.genAt(world, randX, randY, randZ, world.getBlock(randX, randY - 1, randZ), meta, meta == OreDictionary.WILDCARD_VALUE, canSitOn);
                }
            } else {
                // Can we replace it?
                int meta = world.getBlockMetadata(randX, randY, randZ);
                this.genAt(world, randX, randY, randZ, world.getBlock(randX, randY, randZ), meta, meta == OreDictionary.WILDCARD_VALUE, replaceable);
            }
        }
    }

    private void genAt(World world, int targetX, int targetY, int targetZ, Block currentThing, int meta, boolean disregardMeta, List<ItemStack> stacks) {
        for (ItemStack stack : stacks) {
            Block b = Block.getBlockFromItem(stack.getItem());
            if (b == currentThing && (meta == stack.getItemDamage() || disregardMeta)) {
                world.setBlock(targetX, targetY, targetZ, OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.getBlock());
                world.setBlockMetadataWithNotify(targetX, targetY, targetZ, 0, 3);
                break;
            }
        }
    }

    private int makeRandomCoord(int chunkCoord, Random random, int size) {
        return (chunkCoord * 16) + random.nextInt(size);
    }
}
