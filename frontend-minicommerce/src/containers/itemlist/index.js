import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig"
import Button from "../../components/button";
import Modal from "../../components/modal";
import Search from "../../components/search";
import { Badge } from '@mui/material';
import Fab from '@mui/material/Fab';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import ViewStreamIcon from '@mui/icons-material/ViewStream';
import { Link } from "react-router-dom";

class ItemList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			items: [],
			carts: [],
			cartHidden: true,
			isLoading: false,
			isCreate: false,
			isEdit: false,
			id: "",
			title: "",
			price: "",
			description: "",
			category: "",
			quantity: "",
		};
		this.handleClickLoading = this.handleClickLoading.bind(this);
		this.handleAddItem = this.handleAddItem.bind(this);
		this.handleCancel = this.handleCancel.bind(this);
		this.handleChangeField = this.handleChangeField.bind(this);
		this.handleEditItem = this.handleEditItem.bind(this);
		this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
		this.handleSubmitItem = this.handleSubmitItem.bind(this);
		this.handleSearch = this.handleSearch.bind(this);
		this.handleAddToCart = this.handleAddToCart.bind(this);
	}


	componentDidMount() {
		this.loadData();
		this.loadDataCart();
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

	handleAddItem() {
		this.setState({ isCreate: true });
	}

	handleCancel(event) {
		event.preventDefault();
		this.setState({
			isCreate: false,
			isEdit: false,
			id: "",
			title: "",
			price: "",
			description: "",
			category: "",
			quantity: ""
		})
	}

	handleChangeField(event) {
		const { name, value } = event.target;
		this.setState({ [name]: value });
	}

	async loadData() {
		try {
			const { data } = await APIConfig.get("/item");
			this.setState({ items: data.result });
			console.log(data.result)
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
	}

	async loadDataCart() {
		try {
			const { data } = await APIConfig.get("/cart");
			this.setState({ carts: data.result });
			console.log(data.result)
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
	}

	async handleSearch() {
		const value = document.getElementById("search")
		try {
			const { data } = await APIConfig.get(`/item?title=${value.value}`);
			this.setState({ items: data.result });
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
	}

	handleEditItem(item) {
		this.setState({
			isEdit: true,
			id: item.id,
			title: item.title,
			price: item.price,
			description: item.description,
			category: item.category,
			quantity: item.quantity
		})
	}

	async handleSubmitEditItem(event) {
		event.preventDefault();
		try {
			const data = {
				title: this.state.title,
				price: this.state.price,
				description: this.state.description,
				category: this.state.category,
				quantity: this.state.quantity
			};
			await APIConfig.put(`/item/${this.state.id}`, data);
			this.setState({
				id: "",
				title: "",
				price: 0,
				description: "",
				category: "",
				quantity: 0
			})
			this.loadData();
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
		this.handleCancel(event);
	}



	async handleSubmitItem(event) {
		event.preventDefault();
		try {
			const data = {
				title: this.state.title,
				price: this.state.price,
				description: this.state.description,
				category: this.state.category,
				quantity: this.state.quantity
			};
			await APIConfig.post("/item", data);
			this.setState({
				title: "",
				price: 0,
				description: "",
				category: "",
				quantity: 0

			})
			this.loadData();
		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}
		this.handleCancel(event);
	}

	async handleAddToCart(item) {
		try {
			const qty = document.getElementById("cartQty" + item.id)
			if (qty.value != "") { 
			const cartList = [...this.state.carts]
			const data = {
				idItem: item.id,
				quantity: qty.value
			}
			const targetInd = cartList.findIndex((it) => it.item.id === item.id);
			console.log(qty.value)
			if (targetInd >= 0) {
				if (qty.value <= item.quantity - cartList[targetInd].quantity) {
					await APIConfig.post("/cart", data);
					this.loadDataCart();
					alert("Item berhasil ditambahkan pada Cart")
				} else {
					alert("Stok Tidak Memenuhi");
				}
			} else {
				if (qty.value <= item.quantity) {
					await APIConfig.post("/cart", data);
					this.loadDataCart();
					alert("Item berhasil ditambahkan pada Cart")
				} else {
					alert("Stok Tidak Memenuhi");
				}

			}
		} else {
			alert("Silahkan isi kuantitas terlebih dahulu!");
		}

		} catch (error) {
			alert("Oops terjadi masalah pada server");
			console.log(error);
		}


	}

	render() {
		return (
			<div className={classes.itemList}>
				<div style={{ position: 'fixed', top: 25, right: 25 }}>
					<Link to="/cart-item"><Fab variant="extended">
						{this.state.cartHidden ?
							<Badge color="secondary" badgeContent={this.state.carts.length}>
								<ShoppingCartIcon />
							</Badge>
							: <ViewStreamIcon />
						}
					</Fab></Link>
				</div>
				<h1 className={classes.title}>All Items</h1>
				<Button action={this.handleAddItem} className="btn btn-outline-primary">
					Add Item
				</Button>
				<Search action={this.handleSearch}></Search>
				<div>
					{this.state.items.map((item) => (
						<Item
							item={item}
							cart={false}
							handleEdit={() => this.handleEditItem(item)}
							handleAddToCart={() => this.handleAddToCart(item)}
						/>
					))}
				</div>
				<Modal
					show={this.state.isCreate || this.state.isEdit}
					handleCloseModal={this.handleCancel}
					modalTitle={this.state.isCreate ?
						"Add Item" : `Edit item ID ${this.state.id}`}>
					<form>
						<input className={classes.textField}
							type="text"
							placeholder="Nama Item"
							name="title"
							onChange={this.handleChangeField}
							value={this.state.title}></input>

						<input className={classes.textField}
							type="number"
							placeholder="Harga"
							name="price"
							value={this.state.price}
							onChange={this.handleChangeField}></input>

						<textarea className={classes.textField}
							placeholder="Deskripsi"
							name="description"
							rows="4"
							onChange={this.handleChangeField}
							value={this.state.description}></textarea>

						<input className={classes.textField}
							type="text"
							placeholder="Kategori"
							name="category"
							onChange={this.handleChangeField}
							value={this.state.category}></input>

						<input className={classes.textField}
							type="number"
							placeholder="qty"
							name="quantity"
							onChange={this.handleChangeField}
							value={this.state.quantity}></input>

						<Button action={this.state.isCreate
							? this.handleSubmitItem
							: this.handleSubmitEditItem} className="btn btn-outline-primary"
						>
							{this.state.isCreate
								? "Create"
								: "Update"}
						</Button>
						<Button action={this.handleCancel} className="btn btn-outline-danger">Cancel</Button>
					</form>
				</Modal>
			</div>
		);
	}
}
export default ItemList;