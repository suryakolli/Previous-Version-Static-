<?php
	define("host","localhost");
	define("username","1068104");
	define("password","shubham");
	
	mysql_connect(host,username,password) or die("Connection not Successfull");
	mysql_select_db("1068104") or die("Database not found");
	
	$qry = "SELECT * FROM IndiaELearnLogin";
	$result = mysql_query($qry) or die("Query Problem");
	
	if($result){
		echo "Could not Execute Query";
		exit;
	}
	$output = array();
	while($raw = mysql_fetch_array($result)){
		array_push($output,$raw);
	}
	
	echo json_encode($output);
?>