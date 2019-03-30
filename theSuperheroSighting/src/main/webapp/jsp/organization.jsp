<%-- 
    Document   : organization
    Created on : Mar 5, 2019, 11:29:44 AM
    Author     : sonia
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
             <h3>Superhero Sighting</h3>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/displayHomePage">
                            Home
                        </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySuperheroPage">
                         Superhero
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySuperpowerPage">
                         Superpower
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayLocationPage">
                         Location
                      </a>
                  </li>
                  <li role="presentation"
                      class="active">
                      <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                         Organization
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySightingPage">
                         Sighting
                      </a>
                  </li>
                   <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
              </sec:authorize>
                  
                </ul>
               <br>            
                         
             <c:if test="${pageContext.request.userPrincipal.name != null}">
                 
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
              
             </c:if>  
                
            </div>
<div class="row">
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    <div class="col-md-6">
        <h2>Organizations</h2>
        <table id="locationTable" class="table table-hover">
            <tr>
                <th width="40%">Organization Name</th>
                <th width="30%">State</th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
         <tbody>
         <c:forEach var="organization" items="${orgList}">
             <tr>
             <td>
                 <a href="displayOrganizationDetails?organisationId=${organization.organisationId}"> 
                     ${organization.orgName} </a>
             </td>
             <td>${organization.orgState}</td>
             
             <td>
             <sec:authorize access="hasRole('ROLE_ADMIN')">     
             <a href="deleteOrganization?organisationId=${organization.organisationId}">Delete</a> 
             </sec:authorize>
             </td>
             <td>
             <sec:authorize access="hasRole('ROLE_ADMIN')">     
             <a href="displayEditOrganizationForm?organisationId=${organization.organisationId}">Edit</a>
             </sec:authorize>
             </td>
             </tr>
         </c:forEach>
         </tbody>
        </table>                    
    </div> <!-- End col div -->
    <!-- 
        Add col to hold the new organization form - have it take up the other 
        half of the row
    -->
    <div class="col-md-6">
        <sec:authorize access="hasRole('ROLE_ADMIN')"> 
        <h2>Add New Organization</h2>
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createOrganisation">
            <div class="form-group">
                <label for="add-organization-name" class="col-md-4 control-label"> Name:</label>
                <div class="col-md-8">
                  <input type="text" class="form-control" name="organizationName" placeholder="Organization Name"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="add-street" class="col-md-4 control-label">Street:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="street" placeholder="Street"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="city" placeholder="City"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="state" placeholder="State"/>
                </div>
            </div>
            <div class="form-group">
                <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="zip" placeholder="Zip"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="add-contact" class="col-md-4 control-label">Contact:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="contact" placeholder="Contact"/>
                </div>
            </div>
            
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Organization"/>
                </div>
            </div>
        </form>
        </sec:authorize>
    </div> <!-- End col div -->

</div> <!-- End row div -->               
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>