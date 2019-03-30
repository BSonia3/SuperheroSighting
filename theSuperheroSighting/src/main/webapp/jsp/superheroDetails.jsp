<%-- 
    Document   : superheroDetails
    Created on : Mar 5, 2019, 11:35:51 AM
    Author     : sonia
--%>

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
            <h1>Superhero Sighting</h1>
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
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                         Organization
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySightingPage">
                         Sighting
                      </a>
                  </li>
                </ul>    
            </div>
    <div class="row">
        <h4>Superhero Details</h4>
        <br>
    <div class="superheroDetails" >
        
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>Superhero</th>
                            <th>Superpower</th>
                            <th>Organization</th>
                            <th>Street</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Zip Code</th>
                            <th>Contact</th>
                           
                           
                        </tr>                            
                    </thead>
                    <tbody>
                       
                           
                        <c:forEach var="organisation" items="${superhero.orgs}">
                        <tr>
                        <td><c:out value="${superhero.name}"/></td> 
                        <td><c:out value="${superhero.superpower.superpower}"/></td>
                                <td>${organisation.orgName}</td>
                                <td>${organisation.orgStreet}</td>
                                <td>${organisation.orgCity}</td>
                                <td>${organisation.orgState}</td>
                                <td>${organisation.orgZipCode}</td>
                                <td>${organisation.contact}</td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
             
    </div> <!-- End col div -->
   

    </div> <!-- End col div -->

</div> <!-- End row div -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>