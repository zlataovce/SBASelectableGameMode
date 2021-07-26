package me.zlataovce.sbagamemode.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.zlataovce.sbagamemode.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@CommandAlias("sbajoin|joinbw")
public class JoinCommand extends BaseCommand {
    @Dependency private Main plugin;

    @PreCommand
    public boolean onPreCommand(CommandSender sender, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.plugin.getConfig().getString("ingame-only-msg"))));
            return true;
        }
        return false;
    }

    private void sendPlayerToGame(CommandSender sender, int arenaGamemode) {
        final List<String> eligibleArenas = this.plugin.getSbaUtil().getEligibleArenas(arenaGamemode);
        if (eligibleArenas.size() > 0) {
            Bukkit.dispatchCommand(sender, "bw join " + eligibleArenas.get(ThreadLocalRandom.current().nextInt(eligibleArenas.size())));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.plugin.getConfig().getString("no-eligible-arenas-msg"))));
        }
    }

    @Subcommand("solos|singles")
    @Description("Joins the player to a solo arena.")
    public void onSolos(CommandSender sender) {
        this.sendPlayerToGame(sender, 1);
    }

    @Subcommand("doubles|duos")
    @Description("Joins the player to a duo arena.")
    public void onDuos(CommandSender sender) {
        this.sendPlayerToGame(sender, 2);
    }

    @Subcommand("triples|trios")
    @Description("Joins the player to a trio arena.")
    public void onTrios(CommandSender sender) {
        this.sendPlayerToGame(sender, 3);
    }

    @Subcommand("squads")
    @Description("Joins the player to a squad arena.")
    public void onSquads(CommandSender sender) {
        this.sendPlayerToGame(sender, 4);
    }
}
