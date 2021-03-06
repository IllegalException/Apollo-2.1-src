//Decomped by XeonLyfe

package com.apollo.client.module.modules.render;

import com.apollo.client.module.*;
import com.apollo.api.values.*;
import java.util.*;
import com.apollo.api.event.events.*;
import net.minecraft.entity.player.*;
import com.apollo.api.players.friends.*;
import com.apollo.client.module.modules.hud.*;
import com.apollo.api.players.enemy.*;
import java.awt.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class Tracers extends Module
{
    Value.Integer renderDistance;
    Value.Mode pointsTo;
    int tracerColor;
    
    public Tracers() {
        super("Tracers", Module.Category.Render);
    }
    
    public void setup() {
        this.renderDistance = this.registerInteger("Distance", "Distance", 100, 10, 260);
        final ArrayList<String> link = new ArrayList<String>();
        link.add("Head");
        link.add("Feet");
        this.pointsTo = this.registerMode("Draw To", "DrawTo", (List)link, "Feet");
    }
    
    public void onWorldRender(final RenderEvent event) {
        Tracers.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer).filter(e -> e != Tracers.mc.player).forEach(e -> {
            if (Tracers.mc.player.getDistance(e) <= this.renderDistance.getValue()) {
                if (Friends.isFriend(e.getName())) {
                    this.tracerColor = ColorMain.getFriendColorInt();
                }
                else if (Enemies.isEnemy(e.getName())) {
                    this.tracerColor = ColorMain.getEnemyColorInt();
                }
                else {
                    if (Tracers.mc.player.getDistance(e) < 20.0f) {
                        this.tracerColor = Color.RED.getRGB();
                    }
                    if (Tracers.mc.player.getDistance(e) >= 20.0f && Tracers.mc.player.getDistance(e) < 50.0f) {
                        this.tracerColor = Color.YELLOW.getRGB();
                    }
                    if (Tracers.mc.player.getDistance(e) >= 50.0f) {
                        this.tracerColor = Color.GREEN.getRGB();
                    }
                }
                if (this.pointsTo.getValue().equalsIgnoreCase("Head")) {
                    this.drawLineToEntityPlayer(e, this.tracerColor, 255);
                }
                else if (this.pointsTo.getValue().equalsIgnoreCase("Feet")) {
                    this.drawLineToEntityPlayer(e, this.tracerColor, 255);
                }
            }
        });
    }
    
    public void drawLineToEntityPlayer(final Entity e, final int rgb, final int a) {
        final double[] xyz = interpolate(e);
        final int r = rgb >>> 16 & 0xFF;
        final int g = rgb >>> 8 & 0xFF;
        final int b = rgb & 0xFF;
        this.drawLine1(xyz[0], xyz[1], xyz[2], e.height, (float)r, (float)g, (float)b, (float)a);
    }
    
    public static double[] interpolate(final Entity entity) {
        final double posX = interpolate(entity.posX, entity.lastTickPosX) - Tracers.mc.getRenderManager().renderPosX;
        final double posY = interpolate(entity.posY, entity.lastTickPosY) - Tracers.mc.getRenderManager().renderPosY;
        final double posZ = interpolate(entity.posZ, entity.lastTickPosZ) - Tracers.mc.getRenderManager().renderPosZ;
        return new double[] { posX, posY, posZ };
    }
    
    public static double interpolate(final double now, final double then) {
        return then + (now - then) * Tracers.mc.getRenderPartialTicks();
    }
    
    public void drawLine1(final double posx, final double posy, final double posz, final double up, final float red, final float green, final float blue, final float opacity) {
        final Vec3d eyes = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().player.rotationYaw));
        if (this.pointsTo.getValue().equalsIgnoreCase("Head")) {
            renderLine1(eyes.x, eyes.y + Tracers.mc.player.getEyeHeight(), eyes.z, posx, posy, posz, up, red, green, blue, opacity);
        }
        else {
            renderLine2(eyes.x, eyes.y + Tracers.mc.player.getEyeHeight(), eyes.z, posx, posy, posz, up, red, green, blue, opacity);
        }
    }
    
    public static void renderLine1(final double posx, final double posy, final double posz, final double posx2, final double posy2, final double posz2, final double up, final float red, final float green, final float blue, final float opacity) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, opacity);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(posx, posy, posz);
        GL11.glVertex3d(posx2, posy2 + up, posz2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
        GL11.glPopMatrix();
    }
    
    public static void renderLine2(final double posx, final double posy, final double posz, final double posx2, final double posy2, final double posz2, final double up, final float red, final float green, final float blue, final float opacity) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, opacity);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        Tracers.mc.entityRenderer.orientCamera(Tracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(posx, posy, posz);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
        GL11.glPopMatrix();
    }
}
