

    public boolean Checkuser(String username) {
        boolean result = false;

        try {
            ResultSet resultset = database.executeQuery("select * from users where username = '" + username + "';");
            if (!resultset.next()) {
                System.out.println("no result");
            } else {
                    result = true;

            }
        } catch (SQLException var10) {
            result = false;
        } finally {
            //database.close();
        }
        return result;
    }




          function checkname(myform) {
            if (Checkuser(myform.uid.value)) {
              return true;
            }
            else {
              myform.passw.focus();
              alert("there is already someone with this name");
              return false;
            }
          }

