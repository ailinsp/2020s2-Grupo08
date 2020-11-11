
import React from 'react';

const Post = ({ data }) => {
  const { id, description, image_url:portrait, image_url:landscape, likes } = data;

  return (
    <div id={id} className="card beer-card">
      <div className="card-body">
        <div className="row">
          <div className="col-2 beer-col" style={{ textAlign: 'center' }}>
            <img src={portrait} alt={description} className="beer-img" />
          </div>
          <div className="col-10">
            <h5 className="card-title">{description}</h5>
            <p className="card-text">{description}</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Post;