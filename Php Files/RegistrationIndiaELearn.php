<?php
	define("hostname","localhost");
	define("username","1068104");
	define("password","shubham");
	mysql_connect(hostname,username,password) or die("Connection not Successfull");
	mysql_select_db("1068104") or die("Database not found");
	if(isset($_POST['Name']) && isset($_POST['Username']) && isset($_POST['Password']) && isset($_POST['Email'])){
		$name = $_POST['Name'];
		$username = $_POST['Username'];
		$password = $_POST['Password'];
		$email = $_POST['Email'];
		
		if( $name=='' || $username=='' || $password=='' || $email==''){
			echo "Filling all the Fields is mandatory";
		}
		else{
			$qry = "SELECT * FROM IndiaELearnLogin WHERE Username='$username' OR Email='$Email'";
			
			$chck = mysql_query($qry);
			
			if(mysql_num_rows($chck)==0){
				$new_qry = "INSERT INTO IndiaELearnLogin(Name,Username,Password,Email) VALUES('$name','$username','$password','$email'";
				$res = mysql_query($new_qry);
				if($res){
					echo "Successfully Registered";
				}
				else{
					echo "Query Problem";
				}
			}
			else{
				echo "Data Already Exists...Try Other Data";
			}
		}
	}
	else{
		echo "Error";
	}
?>