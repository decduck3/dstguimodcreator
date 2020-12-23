using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace Don_t_Starve_Together_GUI_Mod_Creator
{
	class CodeManager
	{
		public Mod mod;

		private string[] files;
		private string[] allowedExtension =
		{
			"*.lua",
			"*.tex",
			"*.xml",
			"*.zip"
		};


		//Scan in all files
		public void Scan()
		{
			files = Directory.GetFiles(mod.rootDirectory, "*.*", SearchOption.AllDirectories);
		}

		//Clean directory
		public void Clean()
		{
			for (int i = 0; i < files.Length; i++)
			{
				if (!files.Contains(Path.GetExtension(files[i])))
				{
					File.Delete(files[i]);
				}
			}
		}

		//Read modinfo
		public void LoadInfo()
		{
			if(File.Exists(mod.rootDirectory + "/modinfo.lua"))
			{
				StreamReader file = new StreamReader(mod.rootDirectory + @"\modinfo.lua");
				string line;
				while((line = file.ReadLine()) != null)
				{
					if (line.Contains("name") && mod.name == null)
					{
						mod.name = Regex.Match(line, "\".*?\"").Value.Replace("\"", "");
					}
					if (line.Contains("description") && mod.description == null)
					{
						mod.description = Regex.Match(line, "\".*?\"").Value.Replace("\"", "");
					}
					if (line.Contains("author") && mod.author == null)
					{
						mod.author = Regex.Match(line, "\".*?\"").Value.Replace("\"", "");
					}
					if (line.Contains("forumthread") && mod.forumthread == null)
					{
						mod.forumthread = Regex.Match(line, "\".*?\"").Value.Replace("\"", "");
					}
				}
				
			}
		}
	}
}
