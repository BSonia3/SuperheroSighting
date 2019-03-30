<%-- 
    Document   : sighting
    Created on : Mar 5, 2019, 11:31:56 AM
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
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                         Organization
                      </a>
                  </li>
                  <li role="presentation"
                      class="active">
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
              
               <br>        
                <a href="${pageContext.request.contextPath}/displaySightingForm" class="btn btn-link">Create Sighting Form</a>          
                    
            </div>
                         
 
    <div class="row">
                                                                
        <div class="sighting">
        <h5>List of Sightings</h5>
        <table id="superheroTable" class="table table-hover">
            <tr>
                <th width="20%">Date</th>
                <th width="25%"> Superhero</th>
                <th width="25%"> Location</th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
         <tbody>
         <c:forEach var="sighting" items="${sightingList}">
             <tr>
             <td>
                 <a href="displaySightingDetails?sightingId=${sighting.sightingId}"> 
                     ${sighting.sightingDate} </a>
             </td>
             <td>
             <c:forEach var="superhero" items="${sighting.heros}">
              <a href="displaySuperheroDetails?superheroId=${superhero.superheroId}"> 
                      
              <c:out value="${superhero.name}"/></a> 
              <br>
                             
              </c:forEach>
              </td>
              <td>
                <a href="displayLocationDetails?locationId=${sighting.location.locationId}">   
                    <c:out value="${sighting.location.locName}"/> </a> 
              </td>
             <td>
             <sec:authorize access="hasRole('ROLE_ADMIN')">    
             <a href="deleteSighting?sightingId=${sighting.sightingId}">Delete</a> 
             </sec:authorize>
             </td>
             <td>
             <a href="displayEditSightingForm?sightingId=${sighting.sightingId}">Edit</a>
             </td>
             </tr>
         </c:forEach>
         </tbody>
        </table> 
         <!-- picture album-->
         <div>
      <c:forEach var="currentPicture" items="${pictureList}">
                <hr>
                ${currentPicture.title}<br>
                <img src="${pageContext.request.contextPath}/${currentPicture.filename}"
                     class="img-thumbnail" alt="${currentPicture.title}" width="100" height="100"><br> 
      </c:forEach>
      </div> 
      </div>
           
     

    </div> <!-- End row div -->
     </div>           
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>