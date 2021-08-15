package me.jonakls.souplab;

import me.jonakls.souplab.api.Core;
import me.jonakls.souplab.api.Loader;
import me.jonakls.souplab.gui.KitsGUI;
import me.jonakls.souplab.handlers.KillStreakHandler;
import me.jonakls.souplab.loader.CommandsLoader;
import me.jonakls.souplab.loader.FilesLoader;
import me.jonakls.souplab.loader.ListenersLoader;
import me.jonakls.souplab.loader.ManagerLoader;
import me.jonakls.souplab.manager.KillStreakManager;
import me.jonakls.souplab.scoreboard.GameScoreboard;
import me.jonakls.souplab.storage.DataStorage;
import me.jonakls.souplab.storage.PlayerCache;
import me.jonakls.souplab.storage.database.IConnection;
import me.jonakls.souplab.storage.database.types.sqlite.SQLConnection;

public class PluginCore implements Core{

    private final SoupLab plugin;

    private FilesLoader filesLoader;
    private ManagerLoader managerLoader;

    private KitsGUI kitsGUI;
    private KillStreakManager killStreakManager;
    private KillStreakHandler killStreakHandler;
    private PlayerCache playerCache;
    private IConnection connection;
    private DataStorage storage;
    private GameScoreboard gameScoreboard;

    public PluginCore(SoupLab plugin){
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
        this.getPlugin().getLogger().info("Loading database...");
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

    public SoupLab getPlugin(){
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
