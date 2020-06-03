
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demo API</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
</head>
<body>
       <h3>Please enter a range for plant's details information</h3>

      <form action = "/getPlant?from&&to" method = "GET">
        <div class="form-group">
          <label for="form">From:</label>
          <input type="number" class="form-control" name="form" placeholder="Enter integer from range">
           <small id="from" class="form-text text-muted">If blank we fetch all plants</small>
        </div>
        <div class="form-group">
          <label for="to">To:</label>
          <input type="number" class="form-control" name="to" placeholder="Enter integer to range">
          <small id="to" class="form-text text-muted">If blank we fetch all plants</small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>

</body>
</html>