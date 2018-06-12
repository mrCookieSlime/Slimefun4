package me.mrCookieSlime.Slimefun.Events;

import me.mrCookieSlime.Slimefun.Objects.Research;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ResearchUnlockEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	Player p;
	Research r;
	boolean l;
	boolean cancelled;
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public ResearchUnlockEvent(Player p, Research res, boolean lock) {
		this.p = p;
		this.r = res;
		this.l = lock;
	}
	
	public Player getPlayer() {
		return this.p;
	}
	
	public Research getResearch() {
		return this.r;
	}
	
	public boolean isLock() {
		return this.l;	
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
