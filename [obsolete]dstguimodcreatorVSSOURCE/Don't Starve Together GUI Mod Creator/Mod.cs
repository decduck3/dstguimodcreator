using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Don_t_Starve_Together_GUI_Mod_Creator
{
	class Mod
	{
		//Program's values
		public string rootDirectory;
		public CodeManager manager = new CodeManager();

		//All values inside modinfo.lua
		public string name;
		public string description;
		public string author;
		public string version;

		public string forumthread;

		public int api_version;

		public bool client_only_mod;

		public bool dst_compatible;

		public bool all_client_require_mod;

		public string icon;
		public string icon_atlas;

		public string configuration_options;

		public Mod(string directory)
		{
			rootDirectory = directory;
			manager.mod = this;

			manager.Scan();
			manager.LoadInfo();
			Master.modEditor.Controls["modName"].Text = name;
		}
	}
}
