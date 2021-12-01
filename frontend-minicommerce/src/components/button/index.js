import React from "react";
import classes from "./styles.module.css";

const Button = (props) =>{
	const {action, children, style, className} = props;

	return (
		<button className={className} onClick={action} style={style}>{children}</button>
	);
}

export default Button;