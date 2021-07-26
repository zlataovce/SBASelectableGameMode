package me.zlataovce.sbagamemode;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import me.zlataovce.sbagamemode.commands.JoinCommand;
import me.zlataovce.sbagamemode.misc.SBAUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Getter private SBAUtil sbaUtil;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.sbaUtil = new SBAUtil();

        // commands init
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new JoinCommand());
        // commands end
    }
}
