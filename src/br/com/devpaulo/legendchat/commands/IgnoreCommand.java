package br.com.devpaulo.legendchat.commands;

import br.com.devpaulo.legendchat.api.Legendchat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IgnoreCommand implements CommandExecutor
{
  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
  {
    if (sender==Bukkit.getConsoleSender())
    {
      return false;
    }
    if (args.length==0)
    {
      sender.sendMessage(Legendchat.getMessageManager().getMessage("wrongcmd").replace("@command", "/ignore <player>"));
      return true;
    }
    Player p = Bukkit.getPlayer(args[0]);
    if (p==null)
    {
      sender.sendMessage(Legendchat.getMessageManager().getMessage("error8"));
      return true;
    }
    if (p==(Player) sender)
    {
      sender.sendMessage(Legendchat.getMessageManager().getMessage("error9"));
      return true;
    }
    if (Legendchat.getIgnoreManager().hasPlayerIgnoredPlayer((Player) sender, p.getName()))
    {
      Legendchat.getIgnoreManager().playerUnignorePlayer((Player) sender, p.getName());
      sender.sendMessage(Legendchat.getMessageManager().getMessage("message15").replace("@player", p.getName()));
    }
    else
    {
      if (p.hasPermission("legendchat.block.ignore"))
      {
        sender.sendMessage(Legendchat.getMessageManager().getMessage("error10"));
        return true;
      }
      Legendchat.getIgnoreManager().playerIgnorePlayer((Player) sender, p.getName());
      sender.sendMessage(Legendchat.getMessageManager().getMessage("message14").replace("@player", p.getName()));
    }
    return true;
  }
}