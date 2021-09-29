using System;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Lab5
{
    public partial class Index : Page
    {

        private string strConnectionString = ConfigurationManager.ConnectionStrings["Lab6ConnectionString"].ConnectionString;
        private SqlCommand _sqlCommand;
        private SqlDataAdapter _sqlDataAdapter;
        DataSet _dtSet;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                BindStudentData();
            }
            btnUpdate.Visible = false;
            btnAdd.Visible = true;
        }

        private static void ShowAlertMessage(string error)
        {
            Page page = HttpContext.Current.Handler as Page;
            if (page != null)
            {
                error = error.Replace("'", "\'");
                ScriptManager.RegisterStartupScript(page, page.GetType(), "err_msg", "alert('" + error + "');", true);
            }
        }

        public void CreateConnection()
        {
            SqlConnection _sqlConnection = new SqlConnection(strConnectionString);
            _sqlCommand = new SqlCommand();
            _sqlCommand.Connection = _sqlConnection;
        }

        public void OpenConnection()
        {
            _sqlCommand.Connection.Open();
        }

        public void CloseConnection()
        {
            _sqlCommand.Connection.Close();
        }

        public void DisposeConnection()
        {
            _sqlCommand.Connection.Dispose();
        }

        public void BindStudentData()
        {
            try
            {
                CreateConnection();
                OpenConnection();
                _sqlCommand.CommandText = "Sp_GridCrud";
                _sqlCommand.CommandType = CommandType.StoredProcedure;
                _sqlCommand.Parameters.AddWithValue("@Event", "Select");
                _sqlDataAdapter = new SqlDataAdapter(_sqlCommand);
                _dtSet = new DataSet();
                _sqlDataAdapter.Fill(_dtSet);
                grvStudent.DataSource = _dtSet;
                grvStudent.DataBind();
            }
            catch (Exception ex)
            {
                Response.Redirect("The Error is " + ex.Message);
            }
            finally
            {
                CloseConnection();
                DisposeConnection();
            }
        }

        protected void btnAdd_Click(object sender, EventArgs e)
        {
            try
            {
                CreateConnection();
                OpenConnection();
                _sqlCommand.CommandText = "Sp_GridCrud";
                _sqlCommand.CommandType = CommandType.StoredProcedure;
                _sqlCommand.Parameters.AddWithValue("@Event", "Add");
                _sqlCommand.Parameters.AddWithValue("@name", Convert.ToString(txtName.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@surname", Convert.ToString(txtSurname.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@phoneNumber", Convert.ToString(txtPhoneNumber.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@groupNumber", Convert.ToString(txtGroupNumber.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@average", Convert.ToDecimal(txtAverage.Text));
                int result = Convert.ToInt32(_sqlCommand.ExecuteNonQuery());
                if (result > 0)
                {

                    ShowAlertMessage("Record Is Inserted Successfully!");
                    BindStudentData();
                    ClearControls();
                }
                else
                {

                    ShowAlertMessage("Failed!");
                }
            }
            catch (Exception ex)
            {

                ShowAlertMessage("Check your input data! " + ex.Message);

            }
            finally
            {
                CloseConnection();
                DisposeConnection();
            }
        }

        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            try
            {

                CreateConnection();
                OpenConnection();

                _sqlCommand.CommandText = "Sp_GridCrud";
                _sqlCommand.CommandType = CommandType.StoredProcedure;
                _sqlCommand.Parameters.AddWithValue("@Event", "Update");
                _sqlCommand.Parameters.AddWithValue("@name", Convert.ToString(txtName.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@surname", Convert.ToString(txtSurname.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@phoneNumber", Convert.ToString(txtPhoneNumber.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@groupNumber", Convert.ToString(txtGroupNumber.Text.Trim()));
                _sqlCommand.Parameters.AddWithValue("@average", Convert.ToDecimal(txtAverage.Text));
                _sqlCommand.Parameters.AddWithValue("@id", Convert.ToDecimal(Session["id"]));

                int result = Convert.ToInt32(_sqlCommand.ExecuteNonQuery());
                if (result > 0)
                {
                    ShowAlertMessage("Record Is Updated Successfully!");
                    grvStudent.EditIndex = -1;
                    BindStudentData();
                    ClearControls();
                }
                else
                {
                    ShowAlertMessage("Failed!");
                }
            }

            catch (Exception ex)
            {
                ShowAlertMessage("Check your input data! " + ex.Message);
            }
            finally
            {
                CloseConnection();
                DisposeConnection();
            }
        }

        protected void btnReset_Click(object sender, EventArgs e)
        {
            ClearControls();
        }

        public void ClearControls()
        {
            txtName.Text = "";
            txtSurname.Text = "";
            txtPhoneNumber.Text = "";
            txtGroupNumber.Text = "";
            txtAverage.Text = "";
        }

        protected void grvStudent_RowEditing(object sender, GridViewEditEventArgs e)
        {
            btnAdd.Visible = false;
            btnUpdate.Visible = true;

            int RowIndex = e.NewEditIndex;
            Label empid = (Label)grvStudent.Rows[RowIndex].FindControl("lblId");
            Session["id"] = empid.Text;

            txtName.Text = ((Label)grvStudent.Rows[RowIndex].FindControl("lblName")).Text.ToString();
            txtSurname.Text = ((Label)grvStudent.Rows[RowIndex].FindControl("lblSurname")).Text.ToString();
            txtPhoneNumber.Text = ((Label)grvStudent.Rows[RowIndex].FindControl("lblPhoneNumber")).Text.ToString();
            txtGroupNumber.Text = ((Label)grvStudent.Rows[RowIndex].FindControl("lblGroupNumber")).Text.ToString();
            txtAverage.Text = ((Label)grvStudent.Rows[RowIndex].FindControl("lblAverage")).Text.ToString();

        }

        protected void grvStudent_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            try
            {
                CreateConnection();
                OpenConnection();
                Label id = (Label)grvStudent.Rows[e.RowIndex].FindControl("lblId");
                _sqlCommand.CommandText = "Sp_GridCrud";
                _sqlCommand.Parameters.AddWithValue("@Event", "Delete");
                _sqlCommand.Parameters.AddWithValue("@id", Convert.ToInt32(id.Text));
                _sqlCommand.CommandType = CommandType.StoredProcedure;
                int result = Convert.ToInt32(_sqlCommand.ExecuteNonQuery());
                if (result > 0)
                {

                    ShowAlertMessage("Record Is Deleted Successfully!");
                    grvStudent.EditIndex = -1;
                    BindStudentData();
                }
                else
                {
                    lblMessage.Text = "Failed!";
                    lblMessage.ForeColor = System.Drawing.Color.Red;
                    BindStudentData();
                }
            }
            catch (Exception ex)
            {

                ShowAlertMessage("Check your input data! " + ex.Message);
            }
            finally
            {
                CloseConnection();
                DisposeConnection();
            }
        }

        protected void grvStudent_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
        {
            grvStudent.EditIndex = -1;
            BindStudentData();
        }

        protected void grvStudent_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            grvStudent.PageIndex = e.NewPageIndex;
            BindStudentData();
        }
    }
}