<?php
// Include config file
require_once "config.php";
 
// Define variables and initialize with empty values
$name = $lastname = $groupnum = $coursenum = "";
$name_err = $lastname_err = $groupnum_err = $coursenum_err = "";
 
// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){
    // Validate name
    $input_name = trim($_POST["name"]);
    if(empty($input_name)){
        $name_err = "Please enter a name.";
    } elseif(!filter_var($input_name, FILTER_VALIDATE_REGEXP, array("options"=>array("regexp"=>"/^[a-zA-Zа-яА-Я\s]+$/")))){
        $name_err = "Please enter a valid name.";
    } else{
        $name = $input_name;
    }
	
    // Validate lastname
    $input_lastname = trim($_POST["lastname"]);
    if(empty($input_lastname)){
        $lastname_err = "Please enter a lastname.";
    } elseif(!filter_var($input_lastname, FILTER_VALIDATE_REGEXP, array("options"=>array("regexp"=>"/^[a-zA-Zа-яА-Я\s]+$/")))){
        $lastname_err = "Please enter a valid lastname.";
    } else{
        $lastname = $input_lastname;
    }	
    
    // Validate groupnum
    $input_groupnum = trim($_POST["groupnum"]);
    if(empty($input_groupnum)){
        $groupnum_err = "Please enter a groupnum.";
    } elseif(!filter_var($input_groupnum, FILTER_VALIDATE_REGEXP, array("options"=>array("regexp"=>"/^[a-zA-Z0-9\s]+$/")))){
        $groupnum_err = "Please enter a valid groupnum.";
    } else{
        $groupnum = $input_groupnum;
    }	

    // Validate coursenum
    $input_coursenum = trim($_POST["coursenum"]);
    if(empty($input_coursenum)){
        $coursenum_err = "Please enter the coursenum number.";     
    } elseif(!ctype_digit($input_coursenum)){
        $coursenum_err = "Please enter a positive integer value.";
    } else{
        $coursenum = $input_coursenum;
    }
    
    // Check input errors before inserting in database
    if(empty($name_err) && empty($lastname_err) && empty($groupnum_err) && empty($coursenum_err) ){
        // Prepare an insert statement
        $sql = "INSERT INTO students (name, lastname, groupnum, coursenum) VALUES (?, ?, ?, ?)";
         
        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "ssss", $param_name, $param_lastname, $param_groupnum, $param_coursenum);
            
            // Set parameters
            $param_name = $name;
            $param_lastname = $lastname;
            $param_groupnum = $groupnum;
			$param_coursenum = $coursenum;
            
            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Records created successfully. Redirect to landing page
                header("location: index.php");
                exit();
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }
         
        // Close statement
        mysqli_stmt_close($stmt);
    }
    
    // Close connection
    mysqli_close($link);
}
?>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Record</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .wrapper{
            width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="mt-5">Создать запись</h2>
                    <p>Пожалуйста заполните форму для добавления новой записи в БД.</p>
                    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                        <div class="form-group">
                            <label>Имя</label>
                            <input type="text" name="name" class="form-control <?php echo (!empty($name_err)) ? 'is-invalid' : ''; ?>" value="<?php echo $name; ?>">
                            <span class="invalid-feedback"><?php echo $name_err;?></span>
                        </div>
                        <div class="form-group">
                            <label>Фамилия</label>
                            <textarea name="lastname" class="form-control <?php echo (!empty($lastname_err)) ? 'is-invalid' : ''; ?>"><?php echo $lastname; ?></textarea>
                            <span class="invalid-feedback"><?php echo $lastname_err;?></span>
                        </div>
                        <div class="form-group">
                            <label>Номер группы</label>
                            <input type="text" name="groupnum" class="form-control <?php echo (!empty($groupnum_err)) ? 'is-invalid' : ''; ?>" value="<?php echo $groupnum; ?>">
                            <span class="invalid-feedback"><?php echo $groupnum_err;?></span>
                        </div>
                        <div class="form-group">
                            <label>Номер курса</label>
                            <input type="text" name="coursenum" class="form-control <?php echo (!empty($coursenum_err)) ? 'is-invalid' : ''; ?>" value="<?php echo $coursenum; ?>">
                            <span class="invalid-feedback"><?php echo $coursenum_err;?></span>
                        </div>						
                        <input type="submit" class="btn btn-primary" value="Submit">
                        <a href="index.php" class="btn btn-secondary ml-2">Cancel</a>
                    </form>
                </div>
            </div>        
        </div>
    </div>
</body>
</html>