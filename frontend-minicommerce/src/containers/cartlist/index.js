import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig"
import Button from "../../components/button";
import { Badge } from '@mui/material';
import Fab from '@mui/material/Fab';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import ViewStreamIcon from '@mui/icons-material/ViewStream';
import { Link } from "react-router-dom";


class CartList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			carts: [],
			cartHidden: false,
			isLoading: false,
		};
		this.handleClickLoading = this.handleClickLoading.bind(this);
		this.handleCheckout = this.handleCheckout.bind(this);
	}


	componentDidMount() {
		this.loadData();
	}

	shouldComponentUpdate(nextProps, nextState) {
		console.log("shouldComponentUpdate()");
		return true;
	}

	handleClickLoading() {
		const currentLoading = this.state.isLoading;
		this.setState({ isLoading: !currentLoading });
		console.log(this.state.isLoading);
	}

	async loadData() {
		try {
			const { data } = await APIConfig.get("/cart");
			this.setState({ carts: data.result});
			console.log(data.result)
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
	}

	async handleCheckout() {
		try{
			await APIConfig.get("/cart/checkout");
			this.setState({
				carts:[]
			})
			this.loadData();
			alert("Checkout berhasil dilakukan")
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
		
	}


	render() {
		console.log("render()");
		return (
			<div className={classes.itemList}>
				<div style={{ position: 'fixed', top: 25, right: 25 }}>
					<Link to="/list-item"><Fab variant="extended">
						{this.state.cartHidden ?
							<Badge color="secondary" badgeContent={this.state.carts.length}>
								<ShoppingCartIcon />
							</Badge>
							: <ViewStreamIcon />
						}
					</Fab></Link>
				</div>
				<h1 className={classes.title}>Cart Items</h1>
				{this.state.carts.length === 0 ? "" :
				<Button action={this.handleCheckout} className="btn btn-outline-primary">
					Checkout
				</Button>}
				<div>
					{this.state.carts.map((cart) => (
						<Item
							item={cart.item}
							quantityCart = {cart.quantity}
							cart={true}
							totalHarga={cart.item.price * cart.quantity }
						/>
					))}
				</div>
			</div>
		);
	}
}
export default CartList;