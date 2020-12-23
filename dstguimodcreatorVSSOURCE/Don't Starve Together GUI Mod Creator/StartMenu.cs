using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Don_t_Starve_Together_GUI_Mod_Creator
{
	public partial class StartMenu : Form
	{
		public StartMenu()
		{
			InitializeComponent();
		}

		private void newModButton_Click(object sender, EventArgs e)
		{
			Master.NewMod();
		}

		private void loadModButton_Click(object sender, EventArgs e)
		{
			Master.LoadMod();
		}
	}
}
