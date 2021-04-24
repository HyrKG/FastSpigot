package cn.hyrkg.fastspigot.spigot.service;

import cn.hyrkg.fastspigot.innercore.framework.interfaces.IServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public interface IPluginProvider extends IServiceProvider {
    default JavaPlugin getPlugin() {
        return (JavaPlugin) getInnerCore().getCreator();
    }
}