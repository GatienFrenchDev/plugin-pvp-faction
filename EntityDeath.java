package me.gatiendev.pvpfac;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import sun.security.util.Length;

import java.util.concurrent.ThreadLocalRandom;

public class EntityDeath implements Listener{

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        if(e.getEntity().getKiller() instanceof Player){
            int randomNum;
            switch (e.getEntity().getType()){
                case COW:
                    randomNum = ThreadLocalRandom.current().nextInt(1, 100); // une chance sur 50 (2%) de drop une pomme de notch
                    if(randomNum <= 2){
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Rare Drop ! §r§3§kS", "§fPomme de Notch", 20, 60, 20);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
                        break;
                    }
                    else if (randomNum >= 80){
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Lucky Drop ! §r§3§kS", "§fGolden Apple", 20, 60, 20);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
                        break;
                    }
                    e.getDrops().clear();
                    e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, 2));
                    break;
                case SHEEP:
                case PIG:
                case CHICKEN:
                case RABBIT:
                    e.getDrops().clear();
                    e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF, 2));
                    break;
                case SKELETON:
                    randomNum = ThreadLocalRandom.current().nextInt(1, 50); // une chance sur 50 (2%) de drop la LegendaryBokoSword
                    if(randomNum == 1){
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Rare Drop ! §r§3§kS", "§fNether Star", 20, 60, 20);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.NETHER_STAR));
                    }
                    if(randomNum >= 48){ // 10% de chance
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Rare Drop ! §r§3§kS", "§fSkeleton Sword", 20, 60, 20);
                        ItemStack SkeletonSword = new ItemStack(Material.DIAMOND_SWORD);
                        SkeletonSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                        SkeletonSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
                        SkeletonSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), SkeletonSword);
                    }
                    break;
                case SPIDER:
                case WITCH:
                    randomNum = ThreadLocalRandom.current().nextInt(1, 50); // une chance sur 50 (2%) de drop la LegendaryBokoSword
                    if(randomNum == 2){
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Rare Drop ! §r§3§kS", "§fNether Star", 20, 60, 20);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.NETHER_STAR));
                    }
                    break;
                case HUSK:
                case ZOMBIE:
                    randomNum = ThreadLocalRandom.current().nextInt(1, 50); // une chance sur 50 (2%) de drop la LegendaryBokoSword
                    if(randomNum == 1){
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Rare Drop ! §r§3§kS", "§fLegendary Boko Sword", 20, 60, 20);
                        ItemStack LegendaryBokoSword = new ItemStack(Material.DIAMOND_SWORD);
                        LegendaryBokoSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
                        LegendaryBokoSword.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                        LegendaryBokoSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), LegendaryBokoSword);
                        break;
                    }
                    if(randomNum == 2){
                        e.getDrops().clear();
                        e.getEntity().getKiller().sendTitle("§3§kS§r§3 Rare Drop ! §r§3§kS", "§fNether Star", 20, 60, 20);
                        e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(Material.NETHER_STAR));
                    }
                    break;
            }
        }
    }

}
