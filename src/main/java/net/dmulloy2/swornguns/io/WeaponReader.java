package net.dmulloy2.swornguns.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.dmulloy2.swornguns.SwornGuns;
import net.dmulloy2.swornguns.types.EffectType;
import net.dmulloy2.swornguns.types.Gun;
import net.dmulloy2.util.NumberUtil;
import net.dmulloy2.util.Util;

import org.bukkit.Effect;

/**
 * @author dmulloy2
 */

public class WeaponReader
{
	private boolean loaded;

	private File file;
	private Gun ret;

	private final SwornGuns plugin;
	public WeaponReader(SwornGuns plugin, File file)
	{
		this.plugin = plugin;
		this.file = file;

		this.ret = new Gun(file.getName(), plugin);
		this.ret.setFileName(file.getName().toLowerCase());
		this.load();
	}

	public final void load()
	{
		try
		{
			this.loaded = true;
			List<String> file = new ArrayList<String>();
			FileInputStream fstream = new FileInputStream(this.file.getAbsolutePath());
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)
			{
				file.add(strLine);
			}
			br.close();
			in.close();
			fstream.close();

			for (String line : file)
			{
				computeData(line);
			}

			if (ret.getMaterial() == null)
			{
				plugin.getLogHandler().log("Failed to load gun " + this.file.getName() + ": null material!");
				this.loaded = false;
			}
		}
		catch (Throwable ex)
		{
			plugin.getLogHandler().log(Level.SEVERE, Util.getUsefulStack(ex, "loading gun: " + file.getName()));
			this.loaded = false;
		}
	}

	private final void computeData(String str) throws Exception
	{
		if (str.indexOf("=") > 0)
		{
			String var = str.substring(0, str.indexOf("=")).toLowerCase();
			String val = str.substring(str.indexOf("=") + 1);
			if (var.equals("gunname"))
				ret.setName(val);
			if (var.equals("guntype"))
				ret.setGunType(val);
			if (var.equals("ammoamtneeded"))
				ret.setAmmoAmtNeeded(NumberUtil.toInt(val));
			if (var.equals("reloadtime"))
				ret.setReloadTime(NumberUtil.toInt(val));
			if (var.equals("gundamage"))
				ret.setGunDamage(NumberUtil.toDouble(val));
			if (var.equals("armorpenetration"))
				ret.setArmorPenetration(NumberUtil.toDouble(val));
			if (var.equals("ammotype"))
				ret.setAmmoType(val);
			if (var.equals("roundsperburst"))
				ret.setRoundsPerBurst(NumberUtil.toInt(val));
			if (var.equals("maxdistance"))
				ret.setMaxDistance(NumberUtil.toInt(val));
			if (var.equals("bulletsperclick"))
				ret.setBulletsPerClick(NumberUtil.toInt(val));
			if (var.equals("bulletspeed"))
				ret.setBulletSpeed(NumberUtil.toDouble(val));
			if (var.equals("accuracy"))
				ret.setAccuracy(NumberUtil.toDouble(val));
			if (var.equals("accuracy_aimed"))
				ret.setAccuracy_aimed(NumberUtil.toDouble(val));
			if (var.equals("accuracy_crouched"))
				ret.setAccuracy_crouched(NumberUtil.toDouble(val));
			if (var.equals("exploderadius"))
				ret.setExplodeRadius(NumberUtil.toDouble(val));
			if (var.equals("gunvolume"))
				ret.setGunVolume(NumberUtil.toDouble(val));
			if (var.equals("fireradius"))
				ret.setFireRadius(NumberUtil.toDouble(val));
			if (var.equals("flashradius"))
				ret.setFlashRadius(NumberUtil.toDouble(val));
			if (var.equals("canheadshot"))
				ret.setCanHeadshot(Boolean.parseBoolean(val));
			if (var.equals("canshootleft"))
				ret.setCanFireLeft(Boolean.parseBoolean(val));
			if (var.equals("canshootright"))
				ret.setCanFireRight(Boolean.parseBoolean(val));
			if (var.equals("canclickleft"))
				ret.setCanFireLeft(Boolean.parseBoolean(val));
			if (var.equals("canclickright"))
				ret.setCanFireRight(Boolean.parseBoolean(val));
			if (var.equals("knockback"))
				ret.setKnockback(NumberUtil.toDouble(val));
			if (var.equals("recoil"))
				ret.setRecoil(NumberUtil.toDouble(val));
			if (var.equals("canaim"))
				ret.setCanAimLeft(Boolean.parseBoolean(val));
			if (var.equals("canaimleft"))
				ret.setCanAimLeft(Boolean.parseBoolean(val));
			if (var.equals("canaimright"))
				ret.setCanAimRight(Boolean.parseBoolean(val));
			if (var.equals("bullettype"))
				ret.setProjType(val);
			if (var.equals("needspermission"))
				ret.setNeedsPermission(Boolean.parseBoolean(val));
			if (var.equals("hassmoketrail"))
				ret.setHasSmokeTrail(Boolean.parseBoolean(val));
			if (var.equals("gunsound"))
				ret.addGunSounds(val);
			if (var.equals("maxclipsize"))
				ret.setMaxClipSize(NumberUtil.toInt(val));
			if (var.equals("hasclip"))
				ret.setHasClip(Boolean.parseBoolean(val));
			if (var.equals("reloadgunondrop"))
				ret.setReloadGunOnDrop(Boolean.parseBoolean(val));
			if (var.equals("localgunsound"))
				ret.setLocalGunSound(Boolean.parseBoolean(val));
			if (var.equalsIgnoreCase("canGoPastMaxDistance"))
				ret.setCanGoPastMaxDistance(Boolean.parseBoolean(val));
			if (var.equals("bulletdelaytime"))
				ret.setBulletDelayTime(NumberUtil.toInt(val));
			if (var.equals("explosiondamage"))
				ret.setExplosionDamage(NumberUtil.toDouble(val));
			if (var.equals("timeuntilrelease"))
				ret.setReleaseTime(NumberUtil.toInt(val));
			if (var.equals("reloadtype"))
				ret.setReloadType(val);
			if (var.equals("priority"))
				ret.setPriority(NumberUtil.toInt(val));
			if (var.equals("lore"))
				ret.setLore(val);
			if (var.equals("warnifnopermission"))
				ret.setWarnIfNoPermission(Boolean.parseBoolean(val));
			if (var.equals("unlimitedammo"))
				ret.setUnlimitedAmmo(Boolean.parseBoolean(val));
			if (var.equals("explosiontype"))
				ret.setExplosionType(val.toUpperCase());
			if (var.equals("play_effect_on_release"))
			{
				String[] effDat = val.split(",");
				if (effDat.length == 3)
				{
					double radius = NumberUtil.toDouble(effDat[0]);
					int duration = NumberUtil.toInt(effDat[1]);
					Effect eff = Effect.valueOf(effDat[2].toUpperCase());
					EffectType effect = new EffectType(plugin, duration, radius, eff);
					ret.setReleaseEffect(effect);
				}
				else if (effDat.length == 4)
				{
					double radius = NumberUtil.toDouble(effDat[0]);
					int duration = NumberUtil.toInt(effDat[1]);
					Effect eff = Effect.valueOf(effDat[2].toUpperCase());
					byte specialDat = Byte.parseByte(effDat[3]);
					EffectType effect = new EffectType(plugin, duration, radius, eff);
					effect.setSpecialDat(specialDat);
					ret.setReleaseEffect(effect);
				}
			}
		}
	}

	public final boolean isLoaded()
	{
		return loaded;
	}

	public final Gun getGun()
	{
		return ret;
	}
}