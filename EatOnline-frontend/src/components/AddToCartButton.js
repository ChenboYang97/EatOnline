import { useState } from "react";
import { Button, message, Tooltip } from "antd";
import { addItemToCart } from "../utils/apis";
import { PlusOutlined } from "@ant-design/icons";

const AddToCartButton = ({ itemId }) => {
    const [loading, setLoading] = useState(false);
  
    const AddToCart = () => {
      setLoading(true);
      addItemToCart(itemId)
        .then(() => message.success(`Successfully add item`))
        .catch((err) => message.error(err.message))
        .finally(() => {
          setLoading(false);
        });
    };
  
    return (
      <Tooltip title="Add to Cart" placement="top">
        <Button
          loading={loading}
          type="primary"
          icon={<PlusOutlined />}
          onClick={AddToCart}
        />
      </Tooltip>
    );
};

export default AddToCartButton;

