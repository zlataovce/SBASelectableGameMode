package me.zlataovce.sbagamemode.misc;

import io.github.pronze.sba.AddonAPI;
import io.github.pronze.sba.config.SBAConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.screamingsandals.bedwars.api.BedwarsAPI;
import org.screamingsandals.bedwars.api.game.GameStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SBAUtil {
    @Getter private final AddonAPI sbaAPI;
    @Getter private final BedwarsAPI bedwarsAPI;

    public SBAUtil() {
        this.sbaAPI = AddonAPI.getInstance();
        this.bedwarsAPI = BedwarsAPI.getInstance();
    }

    public Map<String, Integer> getMaps() {
        Map<String, Integer> game_size = new HashMap<>();
        SBAConfig.getInstance().node("lobby-scoreboard")
                .node("player-size")
                .node("games")
                .childrenMap()
                .forEach((key, val) -> game_size.put((String) key, val.getInt(4)));
        return game_size;
    }

    public List<String> getEligibleArenas(int arenaGamemode) {
        final List<String> eligibleArenas = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : this.getMaps().entrySet()) {
            if (entry.getValue() == arenaGamemode && this.bedwarsAPI.getGameByName(entry.getKey()) != null && this.bedwarsAPI.getGameByName(entry.getKey()).getStatus().equals(GameStatus.WAITING)) {
                eligibleArenas.add(entry.getKey());
            }
        }
        return eligibleArenas;
    }
}
