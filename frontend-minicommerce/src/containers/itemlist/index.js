import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig"
import Button from "../../components/button";
import Modal from "../../components/modal";

class ItemList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			items: [],
			isLoading: false,
			isCreate: false,
			isEdit: false,
			id: "",
			title: "",
			price: 0,
			description: "",
			category: "",
			quantity: 0,
		};
		this.handleClickLoading = this.handleClickLoading.bind(this);
		this.handleAddItem = this.handleAddItem.bind(this);
		this.handleCancel = this.handleCancel.bind(this);
		this.handleChangeField = this.handleChangeField.bind(this);
		this.handleEditItem = this.handleEditItem.bind(this);
		this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
		this.handleSubmitItem = this.handleSubmitItem.bind(this);
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
			price: 0,
			description: "",
			category: "",
			quantity: 0
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

	render() {
		console.log("render()");
		return (
			<div className={classes.itemList}>
				<h1 className={classes.title}>All Items</h1>
				<Button action={this.handleAddItem}>
					Add Item
				</Button>
				<div>
					{this.state.items.map((item) => (
						<Item
							key={item.id}
							id={item.id}
							title={item.title}
							price={item.price}
							description={item.description}
							category={item.category}
							quantity={item.quantity}
							handleEdit={() => this.handleEditItem(item)}
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
							value={this.state.price === 0 ? "" : this.state.price}
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
							value={this.state.quantity === 0 ? "" : this.state.quantity}></input>

						<Button action={this.state.isCreate
							? this.handleSubmitItem
							: this.handleSubmitEditItem}
						>
							Create
						</Button>
						<Button action={this.handleCancel}>Cancel</Button>
					</form>
				</Modal>
			</div>
		);
	}
}
export default ItemList;