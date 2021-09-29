<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="Lab5.Index" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server"> 

<link href="css/template.css" rel="stylesheet" type="text/css" />  
<link href="css/validationEngine.jquery.css" rel="stylesheet" type="text/css" />  
<script src="js/jquery-1.6.min.js" type="text/javascript"></script>  
<script src="js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>  
<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>  
        <script type="text/javascript">  
    jQuery(document).ready(function () {  
        jQuery("#form1").validationEngine();  
    });  
</script> 

  <fieldset>  
    <legend style="font-family: Arial Black;color:blue; font-size:larger;font-style: oblique">Student CRUD</legend>  
                <table>  
                    <tr>  
                                            
                        <td style="text-align:center">  
                            <asp:TextBox runat="server" ID="txtName" placeholder="Enter Name.." ValidationGroup="add" CssClass="validate[required]"></asp:TextBox>  
                        </td>  
                    </tr>  
                    <tr>  
                          
                        <td style="text-align:center">  
                            <asp:TextBox runat="server" ID="txtSurname" placeholder="Enter Surname.." ValidationGroup="add" CssClass="validate[required]"></asp:TextBox>  
                        </td>  
                    </tr>  
                    <tr>  
                         
                        <td style="text-align:center">  
                            <asp:TextBox runat="server" placeholder="Enter Phone Number.." ID="txtPhoneNumber" ValidationGroup="add" CssClass="validate[required,custom[phone]" ></asp:TextBox>  
                        </td>  
                        <td></td>  
                    </tr>  
                    <tr>  
                         
                        <td style="text-align:center">  
                            <asp:TextBox runat="server" ID="txtGroupNumber" placeholder="Enter Group Number.." ValidationGroup="add" CssClass="validate[required, custom[number]]"></asp:TextBox>  
                        </td>  
                    </tr>  
                    <tr>  
                        
                        <td style="text-align:center">  
                            <asp:TextBox runat="server" ID="txtAverage" placeholder="Enter Average.." ValidationGroup="add" CssClass="validate[required,custom[number]"></asp:TextBox>  
                        </td>  
                    </tr>  
                    <tr>  
                        <td colspan="3">  
                            <asp:Button runat="server" ID="btnAdd" Text="Add" OnClick="btnAdd_Click" class="button button4" ValidationGroup="add"/>  
                            <asp:Button runat="server" ID="btnUpdate" Text="Update" class="button button4" OnClick="btnUpdate_Click"/>  
                            <asp:Button runat="server" ID="btnReset" Text="Reset"  class="button button4" OnClick="btnReset_Click"/>  
                        </td>  
                    </tr>  
                    <tr>  
                        <td colspan="3">  
                            <br />  
                            <asp:Label runat="server" ID="lblMessage"></asp:Label>  
                            <br />  
                            <br />  
  
                        </td>  
                    </tr>  
                    <tr>
                        <td colspan="3">  
                            <asp:GridView ID="grvStudent" runat="server" AllowPaging="true" CellPadding="2" EnableModelValidation="True"  
                                        ForeColor="red" GridLines="Both" ItemStyle-HorizontalAlign="center" EmptyDataText="There Is No Records In Database!" AutoGenerateColumns="false" Width="1100px"  
                                HeaderStyle-ForeColor="blue"   OnPageIndexChanging="grvStudent_PageIndexChanging" OnRowCancelingEdit="grvStudent_RowCancelingEdit" OnRowDeleting="grvStudent_RowDeleting" OnRowEditing="grvStudent_RowEditing">  
                                <HeaderStyle CssClass="DataGridFixedHeader" />  
                                <RowStyle CssClass="grid_item" />  
                                <AlternatingRowStyle CssClass="grid_alternate" />  
                                <FooterStyle CssClass="DataGridFixedHeader" />  
                                <Columns>  
                                    <asp:TemplateField HeaderText="Id">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:Label runat="server" ID="lblId" Text='<%#Eval("id") %>'></asp:Label>  
                                        </ItemTemplate>  
                                    </asp:TemplateField>  
                                    <asp:TemplateField HeaderText="Name">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:Label runat="server" ID="lblName" Text='<%#Eval("SName") %>'></asp:Label>  
                                        </ItemTemplate>  
                                          
                                    </asp:TemplateField>  
                                    <asp:TemplateField HeaderText="Surname">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:Label runat="server" ID="lblSurname" Text='<%#Eval("Surname") %>'></asp:Label>  
                                        </ItemTemplate>  
                                          
                                    </asp:TemplateField>  
                                    <asp:TemplateField HeaderText="Phone No.">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:Label runat="server" ID="lblPhoneNumber" Text='<%#Eval("PhoneNumber") %>'></asp:Label>  
                                        </ItemTemplate>  
                                          
                                    </asp:TemplateField>  
                                    <asp:TemplateField HeaderText="Group No.">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:Label runat="server" ID="lblGroupNumber" Text='<%#Eval("GroupNumber") %>'></asp:Label>  
                                        </ItemTemplate>  
                                          
                                    </asp:TemplateField>  
  
                                    <asp:TemplateField HeaderText="Average">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:Label runat="server" ID="lblAverage" Text='<%#Eval("Average") %>'></asp:Label>  
                                        </ItemTemplate>  
                                        
                                    </asp:TemplateField>  
                                    <asp:TemplateField HeaderText="Update">  
                                         <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>  
                                            <asp:LinkButton runat="server" ID="btnEdit" Text="Edit" CommandName="Edit" ToolTip="Click here to Edit the record" />                                                                                         
                                        </ItemTemplate>  
                                         
                                    </asp:TemplateField>  
                                    <asp:TemplateField HeaderText="Delete">  
                                        <HeaderStyle HorizontalAlign="Left" />  
                                        <ItemStyle HorizontalAlign="Left" />  
                                        <ItemTemplate>                                                                          
                                                <asp:LinkButton runat="server" ID="btnDelete" Text="Delete" CommandName="Delete" OnClientClick="return confirm('Are You Sure You want to Delete the Record?');" ToolTip="Click here to Delete the record" />  
                                            </span>  
                                        </ItemTemplate>                                         
                                    </asp:TemplateField>  
                                </Columns>  
  
                            </asp:GridView>  
                        </td> 
                    </tr>  
                </table>  
      </fieldset>  
    <br />  
    <br />
</asp:Content>