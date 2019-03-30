<%-- 
    Document   : superpower
    Created on : Mar 5, 2019, 11:37:23 AM
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
                  <li role="presentation"
                      class="active">          
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
                             
                                         
        <div class="col-md-6">
        <h3>Superpower</h3>
        <table id="superpowerTable" class="table table-hover">
            <tr>
                <th width="50%">Superpower Name</th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
         <tbody>
         <c:forEach var="superpower" items="${superpowerList}">
             <tr>
             <td>
                 <a href="displaySuperpowerDetails?superpowerId=${superpower.superpowerId}"> 
                     ${superpower.superpower} </a>
             </td>
             <td>
             <a href="deleteSuperpower?superpowerId=${superpower.superpowerId}">Delete</a> 
             </td>
             <td>
             <a href="displayEditSuperpowerForm?superpowerId=${superpower.superpowerId}">Edit</a>
             </td>
             </tr>
         </c:forEach>
         </tbody>
        </table>                    
     </div>
    
    <div class="col-md-6">
        <h3>Add New Superpower</h3>
        
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createSuperpower">
            
            <div class="form-group">
                <label for="add-superpower-name" class="col-md-4 control-label"> Superpower Name:</label>
                <div class="col-md-8">
                  <input type="text" class="form-control" name="superpower" placeholder="Superpower Name"/>
                </div>
            </div>
           
            
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Superpower"/>
                </div>
            </div>
        </form>
     </div> <!-- End col div -->

    </div> <!-- End row div -->
     </div>           
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
