<%@ page import="model.entity.FacilityProfile" %>
<%@ page import="model.entity.RealPerson" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String customerExistsString = (String) request.getAttribute("customerExists");%>
<% boolean customerExist = customerExistsString != null;%>
<% RealPerson firstCustomer = new RealPerson(0L, "", "");%>
<% RealPerson realPerson = !customerExist ? firstCustomer : (RealPerson) request.getAttribute("realPerson"); %>
<%--<% RealCustomer realCustomer = (RealCustomer) request.getAttribute("realCustomer");%>
<% String firstName = realCustomer == null ? "" : realCustomer.getFirstName();%>
<% String lastName = realCustomer == null ? "" : realCustomer.getLastName();%>
<% int customerId = realCustomer == null ? 0 : (int) realCustomer.getId();%>
<% int customerNumber = realCustomer == null ? new Integer("") : realCustomer.getCustomerNumber();%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title> home</title>
    <link rel="stylesheet" href="/style/bootstrap.css"/>
    <link rel="stylesheet" href="/style/bootstrap.min.css"/>
    <link rel="stylesheet" href="/style/bootstrap-theme.css"/>
    <link rel="stylesheet" href="/style/bootstrap-theme.min.css"/>
    <script src="/style/bootstrap.js"></script>
    <script src="/style/bootstrap.min.js.js"></script>
</head>


<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>
    </div>
</nav>
<head lang="fa">
    <meta charset="UTF-8">
    <link href="style/Style.css" rel="stylesheet">
    <title>تعریف پرونده تسهیلاتی</title>
</head>

<div class="pageHeader">
</div>
<div><h6>home</h6></div>
<div class="container">
    <div class="tableBox">

            <input name="action" value="FindCustomer" type="text" hidden>
            <table>
                <tr>
                    <td>شماره مشتری :</td>
                    <br/>
                    <form class="form-group" action="/facilityAccount/findByCustomerNumber.do" method="post">
                        <input class="form-group" name="customerNumber" placeholder="customerNumber" type="text">
                        <input class="btn btn-info" type="submit" value="بازیابی">
                        <tr>
                        <th><input class="form-control" type="text" readonly value="name" readonly > </th>
                        <th><input class="form-control" type="text" readonly value="family" readonly></th>
                </tr>
                    <c:forEach items="${requestScope.realPersons}" var="realPerson">
                  <tr>
                       <td><input type="text" name="name" class="form-control" value="${realPerson.name}" /></td>
                      <td><input type="text" name="family" class="form-control" value="${realPerson.family}"/></td>
                  </tr>
                   </c:forEach>
                </form>

            </table>

        <form action="/facilityAccount/CreatFacilityAccount.do">
            <table>
                <input name="customerId" value="<%=realPerson.getId()%>" type="text" hidden>
                <input name="action" value="createFacilityAccount" type="text" hidden>
                <tr>
                    <td>مدت قرارداد :</td>
                    <td><input name="duration" id="duration" type="text" placeholder="  مدت قرارداد"></td>
                </tr>
                <tr>
                    <td>مبلغ قرارداد :</td>
                    <td><input name="amount" id="amount" type="text" placeholder="  مبلغ قرارداد"></td>
                </tr>
                <td>نوع تسهيلات :</td>
                <td><select name="chosenFacilityProfile" class="dropdown">
                    <c:forEach items="${requestScope.facilityProfile} " var="facilityProfile">
                        <tr>
                            <td><input type="text" name="name" id="id" value="${facilityProfile.facilityName}"/></td>

                        </tr>
                    </c:forEach>
                </select></td>

            </table>
            <input type="submit" class="button" value="ثبت">
        </form>
        <br>
        <br>
        <br>

        <%-- <% boolean customerExists = Boolean.parseBoolean(String.valueOf(request.getAttribute("customerExists")));%>
         <% if (customerExists) {%>
         <% RealCustomer realCustomer = (RealCustomer) request.getAttribute("realCustomer"); %>
         <form action="/CreateLoanFileServlet">
             <input type="text" name="action" value="createLoanFile" hidden>
             <input name="action" value="createLoanFile" type="text" hidden>
             <input name="customerId" value="<%=realCustomer.getId()%>" type="text" hidden>
             <table>
                 <tr>
                     <td>شماره مشتری :</td>
                     <td>
                         <input name="customerNumber" id="customerNumber" type="text"
                                value="<%=realCustomer.getCustomerNumber()%>" readonly>
                     </td>
                     <td>
                         <input type="button" onclick="" class="button" value="بازیابی">
                     </td>
                 </tr>
                 <tr>
                     <td>نام :</td>
                     <td><input name="firstName" id="firstName" type="text"
                                value="<%=realCustomer.getFirstName()%>" readonly></td>
                 </tr>
                 <tr>
                     <td>نام خانوادگی :</td>
                     <td><input name="lastName" id="lastName" type="text"
                                value="<%=realCustomer.getLastName()%>" readonly></td>
                 </tr>
                 <tr>
                     <td>مدت قرارداد :</td>
                     <td><input name="duration" id="duration" type="text" placeholder="مدت قرارداد.."></td>
                 </tr>
                 <tr>
                     <td>مبلغ قرارداد :</td>
                     <td><input name="amount" id="amount" type="text" placeholder="مبلغ قرارداد.."></td>
                 </tr>
                 <% ArrayList<FacilityProfile> loanTypes = (ArrayList<FacilityProfile>) request.getAttribute("loanTypes"); %>
                 <tr>
                     <td>نوع تسهیلات :</td>
                     <td><select name="chosenLoanType">
                         <% for (FacilityProfile facilityProfile : loanTypes) {%>
                         <option value="<%=facilityProfile.getId()%>"><%=facilityProfile.getName()%>
                         </option>
                         <%}%>
                     </select></td>
                 </tr>
             </table>
             <input type="submit" class="button" value="ثبت">
         </form>
         <%}%>
         <br>
         <br>
         <br>


         <% boolean loanTypeExist = Boolean.parseBoolean(String.valueOf(request.getAttribute("loanTypeExist"))); %>
         <% if (loanTypeExist) {%>
         <form action="/CreateLoanFileServlet">
             <input type="text" name="action" value="retrieveCustomer" hidden>
             <table>
                 <tr>
                     <td>شماره مشتری :</td>
                     <td>
                         <input name="customerNumber" type="text" placeholder="شماره مشتری.."
                            oninvalid="alert('فیلد شماره مشتری را جهت بازیابی پرکنید.');" required>
                     </td>
                     <td><input type="submit" class="button" value="بازیابی"></td>
                 </tr>
                 <tr>
                     <td>نام :</td>
                     <td><input name="firstName" type="text" value="" readonly>
                     </td>
                 </tr>
                 <tr>
                     <td>نام خانوادگی :</td>
                     <td><input name="lastName" type="text"
                                value="" readonly></td>
                 </tr>
                 <tr>
                     <td>مدت قرارداد :</td>
                     <td><input name="duration" type="text" placeholder="مدت قرارداد.."></td>
                 </tr>
                 <tr>
                     <td>مبلغ قرارداد :</td>
                     <td><input name="amount" type="text" placeholder="مبلغ قرارداد.."></td>
                 </tr>
                 <% ArrayList<FacilityProfile> loanTypes = (ArrayList<FacilityProfile>) request.getAttribute("loanTypes"); %>
                 <tr>
                     <td>نوع تسهیلات :</td>
                     <td><select name="chosenLoanType">
                         <% for (FacilityProfile facilityProfile : loanTypes) {%>
                         <option value="<%=facilityProfile.getId()%>"><%=facilityProfile.getName()%>
                         </option>
                         <%}%>
                     </select></td>
                 </tr>
             </table>
             <input type="button" onclick="" class="button" value="ثبت">
         </form>
         <br>
         <%}%>--%>
    </div>
</div>

</body>
</html>
