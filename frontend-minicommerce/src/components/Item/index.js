import React from "react";
import Button from "../button";
import classes from "./styles.module.css";
import { MDBInput } from "mdbreact";
const Item = (props) => {
	const { item, handleEdit, cart, handleAddToCart, totalHarga, quantityCart } = props;
	const { id, title, price, description, category, handleDelete, quantity } = item;
	return (

		<div className={classes.item}>
			<h3>{`ID ${id}`}</h3>
			<p>{`Nama Barang: ${title}`}</p>
			<p>{`Harga: ${price}`}</p>
			<p>{`Deskripsi: ${description}`}</p>
			<p>{`Kategori: ${category}`}</p>
			{cart ?
				<p>{`Jumlah: ${quantityCart}`}</p>
				: <p>{`stok: ${quantity}`}</p>
			}
			{cart ?
				<b><p>Total Harga : {totalHarga}</p></b>
				: ""}
			{cart ?
				""
				: <Button action={handleEdit} className="btn btn-primary">
					Edit
				</Button>}
			{cart ?
				"" : <div style={{ display: 'flex' }}>
					<div>
						<MDBInput hint="Qty" type="number" containerClass="active-pink active-pink-2 mt-0 mb-3" id={`cartQty${id}`} required />
					</div>
					<div>
						<Button style={{ padding: ".50rem .50rem" }} action={handleAddToCart} className="btn btn-outline-info">
							Add to Cart
						</Button>
					</div>
				</div>}

		</div>
	);
};
export default Item;