using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Don_t_Starve_Together_GUI_Mod_Creator
{
	static class Master
	{
		public static StartMenu startMenu;
		public static ModEditor modEditor;
		public static ListView modsListing;
		public static Mod currentMod;

		[STAThread]
		public static void Main(string[] args)
		{
			Application.EnableVisualStyles();
			Application.SetCompatibleTextRenderingDefault(false);
			startMenu = new StartMenu();
			modEditor = new ModEditor();
			modsListing = (ListView)startMenu.Controls["CurrentMods"];

			RegisterMods();

			modsListing.Focus();
			if(modsListing.Items.Count > 0)
			{
				modsListing.Items[0].Selected = true;
			}

			ShowForm(startMenu);
		}

		public static void ShowForm(Form form)
		{
			new Thread(delegate ()
			{
				Application.Run(form);
			}).Start();
		}

		public static void NewMod()
		{
			startMenu.Close();
		}

		public static void LoadMod()
		{
			currentMod = new Mod(Directory.GetCurrentDirectory() + @"\mods\" + modsListing.SelectedItems[0].Text);
			startMenu.Close();
			ShowForm(modEditor);
		}

		public static void RegisterMods()
		{
			string[] folders = Directory.GetDirectories(Directory.GetCurrentDirectory() + @"\mods\", "*", System.IO.SearchOption.TopDirectoryOnly);
			for(int i = 0; i < folders.Length; i++)
			{
				modsListing.Items.Add(folders[i].Replace(Directory.GetCurrentDirectory() + @"\mods\", ""));
			}
		}
	}
}
