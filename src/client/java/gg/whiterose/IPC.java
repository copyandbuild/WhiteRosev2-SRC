package gg.whiterose;

import meteordevelopment.discordipc.DiscordIPC;
import meteordevelopment.discordipc.RichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Contract;

import java.time.Instant;

public class IPC {

    public static RichPresence presence = new RichPresence();
    private static final long RPCID = 1245397480607055932L;

    public static boolean isOnMultiplayerServer() {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        ClientWorld world = minecraft.world;
        return world != null && !minecraft.isInSingleplayer();
    }

    public static void suppressConsoleOutput() {
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            public void write(int b) {
                // Keine Ausgabe
            }
        }));

        System.setErr(new java.io.PrintStream(new java.io.OutputStream() {
            public void write(int b) {
                // Keine Fehlerausgabe
            }
        }));
    }

    public static void start() {
        if (!DiscordIPC.start(RPCID, () -> System.out.println("Logged in account: " + DiscordIPC.getUser().username))) {
            System.out.println("Failed to start Discord IPC");
            return;
        }

        presence.setState("WhiteRose Client");
        presence.setLargeImage("large", "WhiteRose Client by Larrox");

        if (!isOnMultiplayerServer() && !MinecraftClient.getInstance().isInSingleplayer()) {
            presence.setDetails("Home screen");
            presence.setSmallImage("mainmenu", "");
        }

        if (isOnMultiplayerServer()) {
            presence.setDetails(MinecraftClient.getInstance().getServer().getName());
            presence.setSmallImage(getPlayerHeadURL(), MinecraftClient.getInstance().player.getName().toString());
        }

        if (MinecraftClient.getInstance().isInSingleplayer()) {
            presence.setDetails("Singleplayer - " + MinecraftClient.getInstance().world);
            presence.setSmallImage(getPlayerHeadURL(), MinecraftClient.getInstance().player.getName().toString());
        }

        presence.setStart(Instant.now().getEpochSecond());
        DiscordIPC.setActivity(presence);
    }



    @Contract(pure = true)
    private static String getPlayerHeadURL() {
        return "https://api.mineatar.io/face/" + MinecraftClient.getInstance().player.getUuidAsString() + "?scale=100";
    }
}