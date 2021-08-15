package me.jonakls.souppvp;

import me.jonakls.souppvp.api.Core;
import me.jonakls.souppvp.api.Loader;
import me.jonakls.souppvp.gui.KitsGUI;
import me.jonakls.souppvp.handlers.KillStreakHandler;
import me.jonakls.souppvp.loader.CommandsLoader;
import me.jonakls.souppvp.loader.FilesLoader;
import me.jonakls.souppvp.loader.ListenersLoader;
import me.jonakls.souppvp.loader.ManagerLoader;
import me.jonakls.souppvp.manager.KillStreakManager;
import me.jonakls.souppvp.scoreboard.GameScoreboard;
import me.jonakls.souppvp.storage.DataStorage;
import me.jonakls.souppvp.storage.PlayerCache;
import me.jonakls.souppvp.storage.database.IConnection;
import me.jonakls.souppvp.storage.database.types.sqlite.SQLConnection;

public class PluginCore implements Core{

    private final SoupPvP plugin;

    private FilesLoader filesLoader;
    private ManagerLoader managerLoader;

    private KitsGUI kitsGUI;
    private KillStreakManager killStreakManager;
    private KillStreakHandler killStreakHandler;
    private PlayerCache playerCache;
    private IConnection connection;
    private DataStorage storage;
    private GameScoreboard gameScoreboard;

    public PluginCore(SoupPvP plugin){
        this.plugin = plugin;
    }

    @Override
    public void init() {
        filesLoader = new FilesLoader(plugin);
        filesLoader.load();

        this.database();
        kitsGUI = new KitsGUI(filesLoader);
        killStreakManager = new KillStreakManager();
        killStreakHandler = new KillStreakHandler(filesLoader, killStreakManager);
        playerCache = new PlayerCache(this);
        gameScoreboard = new GameScoreboard(this);


        managerLoader = new ManagerLoader(this);
        managerLoader.load();

        initLoaders(
                new CommandsLoader(this),
                new ListenersLoader(this));
    }

    private void database() {
        this.getPlugin().getServer().getLogger().info("Loading database...");
        this.connection = new SQLConnection(this);
        this.connection.load();

        this.storage = new DataStorage(this.connection, this);
    }

    public void closeDatabase() {
        this.connection.close();
    }

    private void initLoaders(Loader... loaders){
        for (Loader loader : loaders){
            loader.load();
        }
    }


    public FilesLoader getFilesLoader() {
        return filesLoader;
    }

    public SoupPvP getPlugin(){
        return plugin;
    }

    public ManagerLoader getManagers(){
        return managerLoader;
    }

    public KitsGUI getKitsGUI() {
        return kitsGUI;
    }

    public KillStreakManager getKillStreak() {
        return killStreakManager;
    }

    public KillStreakHandler getKillStreakHandler(){
        return killStreakHandler;
    }

    public PlayerCache getPlayerCache() {
        return playerCache;
    }

    public DataStorage getStorage() {
        return storage;
    }

    public GameScoreboard gameScoreboard(){
        return gameScoreboard;
    }

}
