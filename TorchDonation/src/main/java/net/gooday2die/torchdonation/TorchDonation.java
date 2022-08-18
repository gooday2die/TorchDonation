package net.gooday2die.torchdonation;

/**
 * Torch Donation Plugin
 * Edited Date : 2022-02-09
 * DO NOT REMOVE MESSAGE PREFIXES OF THIS PLUGIN
 *
 * @author Gooday2die @ https://github.com/gooday2die/TorchDonation
 */

import com.github.dockerjava.api.model.Config;
import net.gooday2die.torchdonation.CommandHandler.Redeem;
import net.gooday2die.torchdonation.CommandHandler.Reload;
import net.gooday2die.torchdonation.CommandHandler.SessionQueue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TorchDonation extends JavaPlugin {

    /**
     * A private method that registers commands to Bukkit (or Spigot or Paper) API.
     */
    private void registerCommands() {
        getCommand("redeem").setExecutor(new Redeem(this));
        getCommand("donate").setExecutor(new Redeem(this));
        getCommand("후원").setExecutor(new Redeem(this));
        getCommand("문상").setExecutor(new Redeem(this));
        getCommand("treload").setExecutor(new Reload(this));
    }

    /**
     * A private method that stores config.yml and cookies.json if they exist.
     */
    private void saveFiles() {
       saveDefaultConfig(); // Save default config if it does not exist.
       Path cookiesPath = Paths.get(this.getDataFolder().getAbsolutePath(), "cookies.json");
       if (!new File(cookiesPath.toString()).exists()) {
           saveResource("cookies.json", false);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TorchDonation] " + ChatColor.WHITE + "cookies.json" +
                    "이 존재하지 않습니다. 사용 방법은 https://github.com/MinecraftTorch/TorchDonation/blob/main/Cookies.md 를 확인해주세요!");
       }
    }

    @Override
    public void onEnable() {
        this.registerCommands();
        this.saveFiles();

        ConfigValues.thisPlugin = this;
        ConfigValues.loadConfig();

        if(ConfigValues.useMySQL)
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TorchDonation] " + ChatColor.WHITE + "MySQL 을 사용합니다.");
        else
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TorchDonation] " + ChatColor.WHITE + "Sqlite 를 사용합니다.");

        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TorchDonation] " + ChatColor.WHITE + "플러그인이 로드되었습니다.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[TorchDonation] " + ChatColor.WHITE + "플러그인이 종료되었습니다.");
    }
}
