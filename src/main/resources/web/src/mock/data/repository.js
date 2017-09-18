import Mock from 'mockjs';
import testImage from './../../assets/test1.png';



const Repositories = [];

for (let i = 0; i < 20; i++) {
  Repositories.push(Mock.mock({
    index: Mock.Random.guid(),
    name: Mock.Random.cname(),
    imageUrl: testImage
  }));
}


export {Repositories};
