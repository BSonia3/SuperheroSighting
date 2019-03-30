<%-- 
    Document   : superhero
    Created on : Mar 5, 2019, 11:34:16 AM
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
                  <li role="presentation"
                      class="active">
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
                <a href="${pageContext.request.contextPath}/displaySuperheroForm" class="btn btn-link">Create Superhero Form</a>          
                    
            </div>
                        
 
    <div class="row">
                                                                
        <div class="superhero">
        <h5>The list of Superheros</h5>
        <table id="superheroTable" class="table table-hover">
            <tr>
                <th width="40%">Superhero Name</th>
                <th width="30%"> Description</th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
         <tbody>
         <c:forEach var="superhero" items="${superheroList}">
             <tr>
             <td>
                 <a href="displaySuperheroDetails?superheroId=${superhero.superheroId}"> 
                     ${superhero.name} </a>
             </td>
             <td>${superhero.description}</td>
             
             <td>
             <sec:authorize access="hasRole('ROLE_ADMIN')">     
             <a href="deleteSuperhero?superheroId=${superhero.superheroId}">Delete</a> 
             </sec:authorize>
             </td>
             <td>
             <a href="displayEditSuperheroForm?superheroId=${superhero.superheroId}">Edit</a>
             </td>
             </tr>
          </c:forEach>
          
         </tbody>
        </table>                    
     </div>
     
    <!-- picture album-->
      <div>
      <c:forEach var="currentPicture" items="${pictureList}">
                <hr>
                ${currentPicture.title}<br>
                <img src="${pageContext.request.contextPath}/${currentPicture.filename}"
                     class="img-thumbnail" alt="${currentPicture.title}" width="100" height="100"><br> 
      </c:forEach>
      </div>
    </div> <!-- End row div -->
     </div>           
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>