import React, { Component } from "react";
import Layout from "./components/layout";
import ItemList from "./containers/itemlist";
import CartList from "./containers/cartlist";
import { Route, Switch, Redirect } from 'react-router-dom';
import { BrowserRouter as Router } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Layout>
            <Route exact path="/list-item" component={ItemList}></Route>
            <Route exact path="/cart-item" component={CartList}></Route>
            <Route exact path="/">
              <Redirect to="/list-item"></Redirect>
            </Route>
          </Layout>
        </Switch>
      </Router>
    );
  }
}
export default App;
