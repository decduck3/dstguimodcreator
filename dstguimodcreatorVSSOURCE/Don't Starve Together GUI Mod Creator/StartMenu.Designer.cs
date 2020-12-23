namespace Don_t_Starve_Together_GUI_Mod_Creator
{
	partial class StartMenu
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(StartMenu));
			this.Title = new System.Windows.Forms.Label();
			this.CurrentMods = new System.Windows.Forms.ListView();
			this.Coloumn = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
			this.existingModsTitle = new System.Windows.Forms.Label();
			this.newModButton = new System.Windows.Forms.Button();
			this.loadModButton = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// Title
			// 
			resources.ApplyResources(this.Title, "Title");
			this.Title.Name = "Title";
			// 
			// CurrentMods
			// 
			resources.ApplyResources(this.CurrentMods, "CurrentMods");
			this.CurrentMods.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Coloumn});
			this.CurrentMods.Cursor = System.Windows.Forms.Cursors.Default;
			this.CurrentMods.FullRowSelect = true;
			this.CurrentMods.GridLines = true;
			this.CurrentMods.HideSelection = false;
			this.CurrentMods.MultiSelect = false;
			this.CurrentMods.Name = "CurrentMods";
			this.CurrentMods.UseCompatibleStateImageBehavior = false;
			this.CurrentMods.View = System.Windows.Forms.View.List;
			// 
			// Coloumn
			// 
			resources.ApplyResources(this.Coloumn, "Coloumn");
			// 
			// existingModsTitle
			// 
			resources.ApplyResources(this.existingModsTitle, "existingModsTitle");
			this.existingModsTitle.Name = "existingModsTitle";
			// 
			// newModButton
			// 
			resources.ApplyResources(this.newModButton, "newModButton");
			this.newModButton.Name = "newModButton";
			this.newModButton.UseVisualStyleBackColor = true;
			this.newModButton.Click += new System.EventHandler(this.newModButton_Click);
			// 
			// loadModButton
			// 
			resources.ApplyResources(this.loadModButton, "loadModButton");
			this.loadModButton.Name = "loadModButton";
			this.loadModButton.UseVisualStyleBackColor = true;
			this.loadModButton.Click += new System.EventHandler(this.loadModButton_Click);
			// 
			// StartMenu
			// 
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
			this.BackColor = System.Drawing.SystemColors.Control;
			this.BackgroundImage = global::Don_t_Starve_Together_GUI_Mod_Creator.Properties.Resources.coverimage;
			resources.ApplyResources(this, "$this");
			this.Controls.Add(this.loadModButton);
			this.Controls.Add(this.newModButton);
			this.Controls.Add(this.existingModsTitle);
			this.Controls.Add(this.CurrentMods);
			this.Controls.Add(this.Title);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
			this.MaximizeBox = false;
			this.Name = "StartMenu";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label Title;
		private System.Windows.Forms.ListView CurrentMods;
		private System.Windows.Forms.ColumnHeader Coloumn;
		private System.Windows.Forms.Label existingModsTitle;
		private System.Windows.Forms.Button newModButton;
		private System.Windows.Forms.Button loadModButton;
	}
}