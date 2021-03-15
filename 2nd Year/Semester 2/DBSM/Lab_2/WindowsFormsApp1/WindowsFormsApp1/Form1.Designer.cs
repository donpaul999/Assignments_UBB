namespace WindowsFormsApp1
{
    partial class Form1
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
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.GridProgrammer = new System.Windows.Forms.DataGridView();
            this.GridHoliday = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.GridProgrammer)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GridHoliday)).BeginInit();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.AccessibleDescription = "button_1";
            this.button1.Location = new System.Drawing.Point(567, 73);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 0;
            this.button1.Text = "CONNECT";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // button2
            // 
            this.button2.AccessibleDescription = "button_2";
            this.button2.Location = new System.Drawing.Point(567, 320);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 1;
            this.button2.Text = "UPDATE DB";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click_1);
            // 
            // GridProgrammer
            // 
            this.GridProgrammer.AccessibleName = "GridProgrammer";
            this.GridProgrammer.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.GridProgrammer.Location = new System.Drawing.Point(25, 23);
            this.GridProgrammer.Name = "GridProgrammer";
            this.GridProgrammer.Size = new System.Drawing.Size(485, 178);
            this.GridProgrammer.TabIndex = 2;
            // 
            // GridHoliday
            // 
            this.GridHoliday.AccessibleName = "GridHoliday";
            this.GridHoliday.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.GridHoliday.Location = new System.Drawing.Point(25, 237);
            this.GridHoliday.Name = "GridHoliday";
            this.GridHoliday.Size = new System.Drawing.Size(485, 178);
            this.GridHoliday.TabIndex = 3;
            this.GridHoliday.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView2_CellContentClick);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.GridHoliday);
            this.Controls.Add(this.GridProgrammer);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.GridProgrammer)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GridHoliday)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.DataGridView GridProgrammer;
        private System.Windows.Forms.DataGridView GridHoliday;
    }
}

