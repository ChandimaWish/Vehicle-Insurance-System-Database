<div class="row">

            <!-- Linking the sidebar -->
			<%@include file="./layouts/sidebar.jsp"%>

            <div class="col-md-9">

                <div class="row carousel-holder">

                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="${images}/008.jpg" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="${images}/006.png" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="${images}/007.jpg" alt="">
                                </div>
                            </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>

                </div>

                <div class = "row">
                	
                	<div class = "col-md-6">
                	
                		<div class="panel panel-primary">
  							<div class="panel-heading">User Details</div>
  							<div class="panel-body">
  								Get the list of all registered users, can add new user or edit/delete user information from the sytem.
  							</div>
						</div>
                		
                	</div>
                	
                	<div class = "col-md-6">
                	
                		<div class="panel panel-primary">
  							<div class="panel-heading">Insurance Details</div>
  							<div class="panel-body">
  								View all insurance details for vehicles and users, you can edit or delete the records.
  							</div>
						</div>
                		
                	</div>
                	
                </div>
                
                <div class = "row">
                	<div class = "col-md-6">
                	
                		<div class="panel panel-primary">
  							<div class="panel-heading">Vehicle Details</div>
  							<div class="panel-body">
  								View all registered Vehicles details with associated User registration details, edit or delete records.
  							</div>
						</div>
                		
                	</div>
                	
                	<div class = "col-md-6">
                	
                		<div class="panel panel-primary">
  							<div class="panel-heading">Search Details</div>
  							<div class="panel-body">
  								Search the user, vehicle and insurance details associated with user details and more.
  							</div>
						</div>
                		
                	</div>
                </div>

            </div>

        </div>