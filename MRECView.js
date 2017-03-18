import React, { PropTypes, Component } from 'react';
import { requireNativeComponent, View, DeviceEventEmitter } from 'react-native';

/*var iface = {
  name: 'MrecView',
  propTypes: {
	fix: PropTypes.string,
    ...View.propTypes // include the default view properties
  },
};*/


class MRECView extends Component{
	constructor(props) {
		super(props);
	}
	onMrecLoaded = (e) => {
		if(!this.props.onLoaded)
			return;
		this.props.onLoaded();
	}
	render() {
		return (
			<View stype={this.props.style}>
				<RMrecView  {...this.props}
					onMrecLoaded={this.props.onLoaded}
					onMrecFailedToLoad={this.props.onFailed}
					onMrecShown={this.props.onShown}
					onMrecClicked={this.props.onClicked} />
			</View>
			);
	}
}

MRECView.propTypes = {
	onLoaded: React.PropTypes.func,
	onFailed: React.PropTypes.func,
	onShown: React.PropTypes.func,
	onClicked: React.PropTypes.func,
	...View.propTypes
}

const RMrecView = requireNativeComponent('MrecView', MRECView);

export default MRECView;