import React from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import { IonApp, IonRouterOutlet } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';

/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import "./theme/variables.css";
import Login from "./pages/account/login";
import Register from "./pages/account/register";
import { createTheme, ThemeProvider } from "@mui/material";
import { AuthorizedView} from "./infrastructure";
import IndexPage from "./pages/books/index";
import {BookProvider} from "./todo/BookProvider";
import {BookEdit, BookList} from "./todo";

const authorizedRoutes = <>
  <BookProvider>
    <IonReactRouter>
      <IonRouterOutlet>
        <Route path="*">
          <Redirect to="/books" />
        </Route>
        <Route path="/books" component={BookList} exact={true} />
        <Route path="/book" component={BookEdit} exact={true} />
        <Route path="/book/:id" component={BookEdit} exact={true} />
      </IonRouterOutlet>
    </IonReactRouter>
  </BookProvider>
</>;

const notAuthorizedRoutes = <>
  <Route exact path="/login">
    <Login />
  </Route>
  <Route exact path="/register">
    <Register />
  </Route>
  <Route path="*">
    <Redirect to="/login" />
  </Route>
</>;

const App = () => {
  const theme = createTheme({
    palette: {
      primary: { main: "#111" },
      secondary: { main: "#82b1ff" },
    },
    typography: {
      fontFamily: "Poppins, sans-serif"
    }
  });

  return (
      <IonApp>
        <ThemeProvider theme={theme}>
          <IonReactRouter>
            <IonRouterOutlet>
              <Switch>
                <AuthorizedView
                    authorized={authorizedRoutes}
                    notAuthorized={notAuthorizedRoutes} />
              </Switch>
            </IonRouterOutlet>
          </IonReactRouter>
        </ThemeProvider>
      </IonApp>
  );
}

export default App;
