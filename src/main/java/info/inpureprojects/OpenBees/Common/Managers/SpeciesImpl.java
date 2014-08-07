package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeProduct;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public class SpeciesImpl implements ISpecies {

    private String tag;
    private String unloc;
    private int bodyColor;
    private HashMap<Allele.AlleleTypes, Allele> alleles;
    private List<BeeProduct> products;

    public SpeciesImpl(String tag, String unloc, int bodyColor, HashMap<Allele.AlleleTypes, Allele> alleles, List<BeeProduct> products) {
        this.tag = tag;
        this.unloc = unloc;
        this.bodyColor = bodyColor;
        this.alleles = alleles;
        this.products = products;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public String getUnlocalizedName() {
        return this.unloc;
    }

    @Override
    public int getBodyColor() {
        return this.bodyColor;
    }

    @Override
    public HashMap<Allele.AlleleTypes, Allele> getGenome() {
        return this.alleles;
    }

    @Override
    public NBTTagCompound generateGenericGenome() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("species", this.getTag());
        NBTTagCompound genome = new NBTTagCompound();
        for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
            genome.setString(t.toString(), alleles.get(t).getTag());
        }
        tag.setTag("genome", genome);
        return tag;
    }

    @Override
    public List<BeeProduct> getPotentialProducts() {
        return this.products;
    }
}
