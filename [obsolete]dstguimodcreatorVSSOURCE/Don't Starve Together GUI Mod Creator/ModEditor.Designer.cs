namespace Don_t_Starve_Together_GUI_Mod_Creator
{
	partial class ModEditor
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
			this.modName = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// modName
			// 
			this.modName.Location = new System.Drawing.Point(13, 13);
			this.modName.Name = "modName";
			this.modName.Size = new System.Drawing.Size(100, 20);
			this.modName.TabIndex = 0;
			// 
			// ModEditor
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(800, 450);
			this.Controls.Add(this.modName);
			this.Name = "ModEditor";
			this.Text = "Mod Editor";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.TextBox modName;
	}
}