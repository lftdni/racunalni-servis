<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv = "Content-Language" content = "en"/>
	<meta http-equiv = "Content-Type" content="text/html; charset=utf-8">
	<title>Racunalni servis - prijava</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
<body>

<div class="container-fluid">
<div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main"><!-- Panels Goes Inside -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default" style="margin-top: 50px;">
				<div class="panel-heading">
					<b>Dobrodosli u Racunalni servis</b>
				</div>
					<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<p><strong>Prijava u sustav</strong></p>
							<hr />
							<form action="prijava">
								<div class="form-group">
									<label for="korisnickoIme">Korisnicko ime:</label>
									<input type="text" class="form-control" id="korisnickoIme" name="korisnickoIme" placeholder="Unesite korisnicko ime...">
								</div>
								<div class="form-group">
									<label for="lozinka">Password</label>
									<input type="password" class="form-control" id="lozinka" name="lozinka" placeholder="Unesite vasu lozinku...">
								</div>
								<button type="submit" class="btn btn-default" value="Prijava">Prijava</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>

</body>
</html>