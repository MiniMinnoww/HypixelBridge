package com.miniplugin.org.miniplugin.mapRebuilder;


public class rebuildMap {
    public static void rebuild() {
        clearArena.clear();

        createBridges.createRedBridge();
        createBridges.createBlueBridge();
        createBridges.createWhiteBridge();

        createSpawnAreas.createBlueSpawn();
        createSpawnAreas.createRedSpawn();
    }
}
