import { MDBInput, MDBIcon } from "mdbreact";
const Search = (props) => {
	const {action} = props;
	return (
		<div style={{ display: 'flex', alignItems: "center", justifyContent: "center" }}>
			<div>
				<MDBIcon icon="search" style={{ marginRight: "12px", fontSize: "22px", cursor:"pointer"}} onClick={action} />
			</div>
			<div>
				<MDBInput hint="Search" type="text" containerClass="active-pink active-pink-2 mt-0 mb-3" id="search" />
			</div>
		</div>
	);
}

export default Search;